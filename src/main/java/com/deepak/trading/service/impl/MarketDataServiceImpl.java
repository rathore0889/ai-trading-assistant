package com.deepak.trading.service.impl;

import com.deepak.trading.dto.market.MarketResponse;
import com.deepak.trading.dto.market.StockPriceResponse;
import com.deepak.trading.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class MarketDataServiceImpl implements MarketDataService {

    private final RestClient restClient;

    @Value("${market.api.key}")
    private String apiKey;

    @Override
    public StockPriceResponse getCurrentPrice(String symbol) {

        // API call next step me likhenge
        MarketResponse response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/quote")
                        .queryParam("symbol", symbol)
                        .queryParam("token", apiKey)
                        .build())
                .retrieve()
                .body(MarketResponse.class);

        StockPriceResponse stock = new StockPriceResponse();

        stock.setSymbol(symbol);
        stock.setCurrentPrice(response.getC());

        return stock;
    }
}