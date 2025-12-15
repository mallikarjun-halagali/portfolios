package com.mallikarjun.portfolios.service;

import com.mallikarjun.portfolios.model.*;
import com.mallikarjun.portfolios.model.request.PortfolioRequest;
import com.mallikarjun.portfolios.model.request.StocksRequest;
import com.mallikarjun.portfolios.repository.PortfolioRepository;
import com.mallikarjun.portfolios.service.externalAPI.FinHubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final FinHubService finHubService;


    public Mono<Portfolio> createPortfolio(PortfolioRequest request) {

        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioId(request.getUserId() + "-" + request.getPortfolioName());
        portfolio.setName(request.getPortfolioName());
        portfolio.setUserId(request.getUserId());
        portfolio.setLastUpdated(Instant.now());

        return buildStocksList(request)
                .flatMap(stocksList -> {
                    portfolio.setStocksList(stocksList);

                    // reactive investedValue
                    portfolio.setInvestedValue(
                            stocksList.stream()
                                    .mapToDouble(Stocks::getInvestedValue)
                                    .sum()
                    );

                    // Optionally compute currentValue if needed
                    portfolio.setCurrentValue(
                            stocksList.stream()
                                    .mapToDouble(Stocks::getCurrentValue)
                                    .sum()
                    );

                    return portfolioRepository.save(portfolio);
                });
    }

    private Mono<List<Stocks>> buildStocksList(PortfolioRequest request) {

        return Flux.fromIterable(request.getStocks())
                .flatMap(stockReq ->
                        finHubService.stockQuote(stockReq.getIsin())
                                .map(quote -> buildStock(stockReq, quote))
                                .onErrorResume(ex -> {
                                    log.error("Failed retrieving quote for ISIN {}. Using fallback.",
                                            stockReq.getIsin(), ex);
                                    return Mono.just(buildStockFallback(stockReq));
                                })
                )
                .collectList();
    }
    private Stocks buildStock(StocksRequest req, FinHubStockQuote quote) {
        Stocks stock = new Stocks();
        stock.setIsin(req.getIsin());
        stock.setName(req.getName());
        stock.setQuantity(req.getQuantity());
        stock.setInvestedValue(req.getInvestedValue());
        stock.setAveragePrice(req.getInvestedValue() / req.getQuantity());
        stock.setCurrentValue(quote.getCurrentPrice() * req.getQuantity());
        return stock;
    }

    private Stocks buildStockFallback(StocksRequest req) {
        Stocks stock = new Stocks();
        stock.setIsin(req.getIsin());
        stock.setName(req.getName());
        stock.setQuantity(req.getQuantity());
        stock.setInvestedValue(req.getInvestedValue());
        stock.setAveragePrice(req.getInvestedValue() / req.getQuantity());
        stock.setCurrentValue(stock.getAveragePrice() * req.getQuantity());
        return stock;
    }

    public Mono<Portfolio> getPortfolioById(String portfolioId) {
        return portfolioRepository.findByPortfolioId(portfolioId);
    }

    public Mono<Portfolio> getPortfoliosByUserId(String userId) {
        return portfolioRepository.getByUserId(userId);
    }

    public Mono<?> getDailyPerformance(String portfolioId) {

        log.info("Initiating daily performance fetch for portfolioId={}", portfolioId);

        return getPortfolioById(portfolioId)
                .switchIfEmpty(Mono.error(new RuntimeException("Portfolio not found")))
                .flatMap(portfolio ->
                        Flux.fromIterable(portfolio.getStocksList())
                                .flatMap(stock ->
                                        finHubService.stockQuote(stock.getIsin())
                                                .onErrorReturn(fallbackStockQuote(stock))
                                                .map(quote -> new StockPriceResult(stock, quote))
                                )
                                .collectList()
                                .map(results -> calculateDailyPerformance(portfolio, results))
                                .subscribeOn(Schedulers.parallel())
                                .flatMap(portfolioRepository::save) // IMPORTANT
                )
                .onErrorResume(ex -> Mono.just(fallbackPortfolioResponse()));

//        return getPortfolioById(portfolioId)
//                .switchIfEmpty(
//                        Mono.defer(() -> {
//                            log.warn("Portfolio not found for id={}", portfolioId);
//                            return Mono.error(new RuntimeException("Portfolio not found"));
//                        })
//                )
//                .flatMap(portfolio -> {
//
//                    log.info("Fetched portfolio. Calculating performance for {} stocks.",
//                            portfolio.getStocksList().size());
//
//                    Mono<Portfolio> map = Flux.fromIterable(portfolio.getStocksList())
//
//                            // Fetch quotes in parallel with error fallback per stock
//                            .flatMap(stock ->
//                                    finHubService.stockQuote(stock.getIsin())
//                                            .doOnSubscribe(sub ->
//                                                    log.debug("Fetching quote for ISIN={}", stock.getIsin()))
//                                            .doOnError(ex ->
//                                                    log.error("Error fetching quote for ISIN={}: {}",
//                                                            stock.getIsin(), ex.getMessage()))
//                                            .onErrorReturn(fallbackStockQuote(stock)) // fallback for failed API call
//                                            .map(quote -> new StockPriceResult(stock, quote))
//                            )
//
//                            .collectList()
//                            .map(results -> calculateDailyPerformance(portfolio, results));
//                    return map;
//                })
//
//                // Global error handler — if anything above fails
//                .onErrorResume(ex -> {
//                    log.error("Failed to fetch daily performance for portfolioId={} | error={}",
//                            portfolioId, ex.getMessage(), ex);
//
//                    return Mono.just(fallbackPortfolioResponse());
//                });
    }

    private Portfolio calculateDailyPerformance(
            Portfolio portfolio,
            List<StockPriceResult> results
    ) {
        double investedValue = portfolio.getInvestedValue();
        double currentValue = 0.0;
        List<Stocks> stocksList = new ArrayList<>();

        for (StockPriceResult result : results) {
            Stocks stock = result.stock();
            FinHubStockQuote quote = result.quote();
            double stockCurValue = stock.getQuantity() * quote.getCurrentPrice();
            stock.setCurrentValue(stockCurValue);

            stocksList.add(stock);

            currentValue += stockCurValue;
        }

        double dailyChange = currentValue - portfolio.getCurrentValue();
        double dailyChangePercent = (dailyChange / portfolio.getCurrentValue()) * 100.0;

        portfolio.setStocksList(stocksList);
        portfolio.setCurrentValue(currentValue);
        portfolio.setLastUpdated(Instant.now());
        portfolio.setDailyChange(dailyChange);
        portfolio.setDailyChangePercent(dailyChangePercent +"%");
        portfolio.setProfitLoss(currentValue - investedValue);

//        portfolioRepository.save(portfolio).subscribe();// update current values concurrently
        return portfolio;

    }

    /** Per-stock fallback quote */
    private FinHubStockQuote fallbackStockQuote(Stocks stock) {
        log.warn("Returning fallback quote for ISIN={} (zeroed values)", stock.getIsin());
        FinHubStockQuote fallback = new FinHubStockQuote();
        fallback.setCurrentPrice(stock.getAveragePrice()); // at least use known data
        fallback.setPercentChange(0);
        fallback.setChange(0);
        return fallback;
    }

    /** Global fallback response */
    private Portfolio fallbackPortfolioResponse() {
        return new Portfolio();
    }

}
