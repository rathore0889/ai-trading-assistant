package com.deepak.trading.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradingAnalysisCompletedEvent {

    private String symbol;

    private String recommendation;

    private Double currentPrice;

    private Double targetPrice;

    private Double stopLoss;

    private String analysis;

    private LocalDateTime analysisTime;

}