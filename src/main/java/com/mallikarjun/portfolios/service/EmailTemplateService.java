package com.mallikarjun.portfolios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailTemplateService {

    private final TemplateEngine templateEngine;

    public String buildDailySummary(Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        return templateEngine.process("daily-summary", context);
    }
}
