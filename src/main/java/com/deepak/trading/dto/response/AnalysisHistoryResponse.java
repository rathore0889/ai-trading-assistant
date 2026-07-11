package com.deepak.trading.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisHistoryResponse {

    private String symbol;

    private Double buyPrice;

    private Double currentPrice;

    private String recommendation;

    private Integer confidence;

    private String risk;

    private String reason;

    private LocalDateTime createdAt;
}