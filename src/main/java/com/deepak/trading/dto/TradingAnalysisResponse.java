package com.deepak.trading.dto;

import lombok.Data;

@Data
public class TradingAnalysisResponse {

    private String recommendation;
    private Integer confidence;
    private String risk;
    private String reason;
}