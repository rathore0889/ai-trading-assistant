package com.deepak.trading.service.impl;

import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.FinnhubQuoteResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;
import com.deepak.trading.mapper.StockMapper;
import com.deepak.trading.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public CompanyProfileResponse getCompanyProfile(String symbol) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock/profile2")
                        .queryParam("symbol", symbol)
                        .queryParam("token", apiKey)
                        .build())
                .retrieve()
                .body(CompanyProfileResponse.class);
    }

    @Override
    public List<CompanyNewsResponse> getCompanyNews(String symbol) {

        LocalDate today = LocalDate.now();

        LocalDate from = today.minusDays(7);

        CompanyNewsResponse[] response =
                restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/company-news")
                                .queryParam("symbol", symbol)
                                .queryParam("from", from)
                                .queryParam("to", today)
                                .queryParam("token", apiKey)
                                .build())
                        .retrieve()
                        .body(CompanyNewsResponse[].class);

        return Arrays.asList(response);
    }
}