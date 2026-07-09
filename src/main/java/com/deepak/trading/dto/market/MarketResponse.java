package com.deepak.trading.dto.market;

import lombok.Data;

@Data
public class MarketResponse {

    // Current Price
    private Double c;

    // Change
    private Double d;

    // Percentage Change
    private Double dp;

    // High
    private Double h;

    // Low
    private Double l;

    // Open
    private Double o;

    // Previous Close
    private Double pc;

    // Timestamp
    private Long t;
}