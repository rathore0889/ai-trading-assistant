package com.deepak.trading.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String TRADING_ANALYSIS_TOPIC =
            "trading-analysis-topic";

    public static final String ALERT_TRIGGERED_TOPIC =
            "alert-triggered-topic";

    public static final String STOCK_ALERT_TOPIC =
            "stock-alert-topic";

    @Bean
    public NewTopic tradingAnalysisTopic() {

        return new NewTopic(
                TRADING_ANALYSIS_TOPIC,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic alertTriggeredTopic() {

        return new NewTopic(
                ALERT_TRIGGERED_TOPIC,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic stockAlertTopic() {

        return new NewTopic(
                STOCK_ALERT_TOPIC,
                3,
                (short) 1
        );
    }

}