package com.deepak.trading.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TradingAnalysisRequest {

    @NotBlank(message = "Stock symbol is required")
    private String symbol;

    @Positive(message = "Buy price must be greater than 0")
    private double buyPrice;

    @Positive(message = "Quantity must be greater than 0")
    private int quantity;

}