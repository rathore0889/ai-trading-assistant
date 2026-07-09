package com.deepak.trading.service.impl;

import com.deepak.trading.dto.market.StockPriceResponse;
import com.deepak.trading.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class MarketDataServiceImpl implements MarketDataService {

    private final RestClient restClient;

    @Override
    public StockPriceResponse getCurrentPrice(String symbol) {

        // API call next step me likhenge
        return null;
    }
}