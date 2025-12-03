package com.mallikarjun.portfolios.service;

import com.mallikarjun.portfolios.model.PortfolioProfile;
import com.mallikarjun.portfolios.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;


    public Mono<?> createPortfolio(PortfolioProfile portfolioProfile) {
        Instant now = Instant.now();
        if (portfolioProfile.getCreatedAt()  == null) {
            portfolioProfile.setCreatedAt(now);
            portfolioProfile.setUpdatedAt(now);
        } else {
            portfolioProfile.setUpdatedAt(now);
        }
        return portfolioRepository.save(portfolioProfile);
    }

    public Flux<?> getAllPortfolios() {
        return portfolioRepository.findAll();
    }
}
