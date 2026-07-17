package com.deepak.trading.event;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StockAlertTriggeredEvent {

    private Long alertId;

    private Long userId;

    private String userEmail;

    private String symbol;

    private BigDecimal currentPrice;

    private BigDecimal targetPrice;

    private String condition;
}