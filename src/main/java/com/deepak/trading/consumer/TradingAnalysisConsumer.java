package com.deepak.trading.consumer;

import com.deepak.trading.config.KafkaConfig;
import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import com.deepak.trading.notification.EmailService;
import com.deepak.trading.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradingAnalysisConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = KafkaConfig.TRADING_ANALYSIS_TOPIC)
    public void consume(
            TradingAnalysisCompletedEvent event) {

        log.info("Trading Analysis Event Received");

        try {
            notificationService.notifyUser(event);
        } catch (Exception ex) {
            log.error("Notification failed", ex);
        }
    }
}