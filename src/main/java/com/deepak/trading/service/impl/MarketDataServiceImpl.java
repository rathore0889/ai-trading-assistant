package com.deepak.trading.service.impl;

import com.deepak.trading.dto.market.FinnhubQuoteResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;
import com.deepak.trading.mapper.StockMapper;
import com.deepak.trading.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class MarketDataServiceImpl implements MarketDataService {

    private final RestClient restClient;
    private final StockMapper stockMapper;

    @Value("${market.api.key}")
    private String apiKey;

    @Override
    public StockQuoteResponse getQuote(String symbol) {

        FinnhubQuoteResponse quote = restClient.get()
                .uri("https://finnhub.io/api/v1/quote?symbol={symbol}&token={token}",
                        symbol, apiKey)
                .retrieve()
                .body(FinnhubQuoteResponse.class);

        return stockMapper.toResponse(quote);
    }
}