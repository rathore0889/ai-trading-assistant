package com.deepak.trading.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TradingAnalysisRequest {

    @NotBlank
    private String symbol;

    @Positive
    private double buyPrice;

    @Positive
    private int quantity;

}