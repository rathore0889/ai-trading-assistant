package com.deepak.trading.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String TRADING_ANALYSIS_TOPIC =
            "trading-analysis-topic";

    @Bean
    public NewTopic tradingAnalysisTopic() {

        return new NewTopic(
                TRADING_ANALYSIS_TOPIC,
                3,
                (short) 1
        );
    }

}