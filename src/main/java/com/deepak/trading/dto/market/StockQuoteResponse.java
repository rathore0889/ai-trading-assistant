package com.deepak.trading.dto.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockQuoteResponse {

    private Double currentPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double openPrice;
    private Double previousClose;
}
