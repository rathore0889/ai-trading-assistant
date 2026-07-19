package com.deepak.trading.notification.impl;

import com.deepak.trading.event.StockAlertTriggeredEvent;
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

    @Override
    public void sendStockAlertEmail(
            StockAlertTriggeredEvent event) {

        log.info("========================================");
        log.info("STOCK ALERT EMAIL");
        log.info("To            : {}", event.getUserEmail());
        log.info("Symbol        : {}", event.getSymbol());
        log.info("Current Price : {}", event.getCurrentPrice());
        log.info("Target Price  : {}", event.getTargetPrice());
        log.info("Condition     : {}", event.getCondition());
        log.info("========================================");

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(event.getUserEmail());

        message.setSubject(
                "Stock Alert Triggered : " + event.getSymbol());

        message.setText(
                "Your alert has been triggered.\n\n"
                        + "Symbol : " + event.getSymbol() + "\n"
                        + "Current Price : " + event.getCurrentPrice() + "\n"
                        + "Target Price : " + event.getTargetPrice() + "\n"
                        + "Condition : " + event.getCondition());

        mailSender.send(message);

        log.info("Stock Alert Email Sent");
    }
}