package com.deepak.trading.dto.market;

import lombok.Data;

@Data
public class StockPriceResponse {

    private String symbol;
    private Double currentPrice;

}