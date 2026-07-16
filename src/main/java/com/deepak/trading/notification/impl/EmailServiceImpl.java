package com.deepak.trading.notification.impl;

import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import com.deepak.trading.notification.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendTradingAnalysisEmail(
            TradingAnalysisCompletedEvent event) {

        log.info("========================================");
        log.info("EMAIL NOTIFICATION");
        log.info("To            : demo@trading.com");
        log.info("Symbol        : {}", event.getSymbol());
        log.info("Recommendation: {}", event.getRecommendation());
        log.info("Current Price : {}", event.getCurrentPrice());
        log.info("Analysis      : {}", event.getAnalysis());
        log.info("========================================");

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo("rathore0889@gmail.com");

        message.setSubject(
                "Trading Recommendation : "
                        + event.getSymbol());

        message.setText(
                """
                Symbol : %s
        
                Recommendation : %s
        
                Current Price : %.2f
        
                Analysis :
        
                %s
                """
                        .formatted(
                                event.getSymbol(),
                                event.getRecommendation(),
                                event.getCurrentPrice(),
                                event.getAnalysis()
                        ));

        mailSender.send(message);

        log.info("Email sent successfully");

    }
}