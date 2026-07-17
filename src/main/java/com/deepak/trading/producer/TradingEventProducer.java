package com.deepak.trading.producer;

import com.deepak.trading.config.KafkaConfig;
import com.deepak.trading.event.StockAlertTriggeredEvent;
import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradingEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishAnalysisCompleted(
            TradingAnalysisCompletedEvent event) {

        log.info("Publishing Trading Analysis Event for {}",
                event.getSymbol());

        kafkaTemplate.send(
                KafkaConfig.TRADING_ANALYSIS_TOPIC,
                event.getSymbol(),
                event);

    }

    public void publishStockAlertTriggered(
            StockAlertTriggeredEvent event) {

        log.info("Publishing Stock Alert Event for {}",
                event.getSymbol());

        kafkaTemplate.send(
                KafkaConfig.STOCK_ALERT_TOPIC,
                event.getSymbol(),
                event
        );
    }

}