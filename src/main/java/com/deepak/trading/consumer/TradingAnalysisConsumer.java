package com.deepak.trading.consumer;

import com.deepak.trading.config.KafkaConfig;
import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import com.deepak.trading.notification.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradingAnalysisConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = KafkaConfig.TRADING_ANALYSIS_TOPIC)
    public void consume(
            TradingAnalysisCompletedEvent event) {

        log.info("=======================================");
        log.info("Trading Analysis Event Received");
        log.info("Symbol         : {}", event.getSymbol());
        log.info("Recommendation : {}", event.getRecommendation());
        log.info("Current Price  : {}", event.getCurrentPrice());
        log.info("Analysis Time  : {}", event.getAnalysisTime());
        log.info("=======================================");

        try {
            emailService.sendTradingAnalysisEmail(event);
        } catch (Exception ex) {
            log.error("Unable to send email notification", ex);
        }
    }
}