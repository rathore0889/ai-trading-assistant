package com.deepak.trading.notification.impl;

import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import com.deepak.trading.notification.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

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

    }
}