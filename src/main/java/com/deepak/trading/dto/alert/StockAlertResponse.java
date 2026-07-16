package com.deepak.trading.dto.alert;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StockAlertResponse {

    private Long id;

    private String symbol;

    private BigDecimal targetPrice;

    private String condition;

    private boolean triggered;
}