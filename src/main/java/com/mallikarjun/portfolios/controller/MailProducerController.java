package com.mallikarjun.portfolios.controller;

import com.mallikarjun.portfolios.service.MailProducerService;
import com.mallikarjun.portfolios.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class MailProducerController {

    private final MailProducerService mailProducerService;

    @GetMapping("/day/{portfolioId}")
    public Object sendDailyMail(@PathVariable String portfolioId){
        return mailProducerService.getDailyMailDataAndSend(portfolioId);
    }
}
