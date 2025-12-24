package com.mallikarjun.portfolios.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final EmailTemplateService templateService;

    public Mono<Void> sendDailySummaryEmail(
            String to,
            Map<String, Object> data
    ) {
        return Mono.fromRunnable(() -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper =
                        new MimeMessageHelper(message, true);

                helper.setTo(to);
                helper.setSubject("Daily Portfolio Summary");
                helper.setText(
                        templateService.buildDailySummary(data),
                        true
                );

                mailSender.send(message);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
