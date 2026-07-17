package com.deepak.trading.consumer;

import com.deepak.trading.event.StockAlertTriggeredEvent;
import com.deepak.trading.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockAlertConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "stock-alert-topic",
            groupId = "stock-alert-group"
    )
    public void consume(StockAlertTriggeredEvent event) {

        log.info("Stock Alert Received for {}", event.getSymbol());

        notificationService.notifyAlert(event);

    }

}