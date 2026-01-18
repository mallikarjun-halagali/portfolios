package com.mallikarjun.portfolios.controller;

import com.mallikarjun.portfolios.model.Portfolio;
import com.mallikarjun.portfolios.model.request.PortfolioRequest;
import com.mallikarjun.portfolios.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }
}