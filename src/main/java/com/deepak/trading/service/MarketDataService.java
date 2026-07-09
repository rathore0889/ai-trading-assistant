package com.deepak.trading.service;

import com.deepak.trading.dto.market.StockPriceResponse;

public interface MarketDataService {

    StockPriceResponse getCurrentPrice(String symbol);

}