package com.deepak.trading.service.impl;

import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.FinnhubQuoteResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;
import com.deepak.trading.exception.MarketDataException;
import com.deepak.trading.mapper.StockMapper;
import com.deepak.trading.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MarketDataServiceImpl implements MarketDataService {

    private static final Logger log =
            LoggerFactory.getLogger(MarketDataServiceImpl.class);

    private final RestClient restClient;
    private final StockMapper stockMapper;

    @Value("${market.api.key}")
    private String apiKey;

    @Override
    @Cacheable(value = "quotes", key = "#symbol")
    public StockQuoteResponse getQuote(String symbol) {

        log.info("Fetching quote from Finnhub for {}", symbol);

        try {
            FinnhubQuoteResponse quote = restClient.get()
                    .uri("https://finnhub.io/api/v1/quote?symbol={symbol}&token={token}",
                            symbol, apiKey)
                    .retrieve()
                    .body(FinnhubQuoteResponse.class);

            return stockMapper.toResponse(quote);

        } catch (Exception e) {

            throw new MarketDataException(
                    "Unable to fetch stock quote for : " + symbol,
                    e
            );
        }
    }

    @Async
    @Override
    public CompletableFuture<StockQuoteResponse> getQuoteAsync(String symbol) {

        return CompletableFuture.completedFuture(
                getQuote(symbol)
        );
    }

    @Async
    @Override
    public CompletableFuture<CompanyProfileResponse> getCompanyProfileAsync(String symbol) {

        return CompletableFuture.completedFuture(
                getCompanyProfile(symbol)
        );
    }

    @Async
    @Override
    public CompletableFuture<List<CompanyNewsResponse>> getCompanyNewsAsync(String symbol) {

        return CompletableFuture.completedFuture(
                getCompanyNews(symbol)
        );
    }

    @Override
    @Cacheable(value = "profiles", key = "#symbol")
    public CompanyProfileResponse getCompanyProfile(String symbol) {

        log.info("Fetching profile from Finnhub for {}", symbol);
        try {

            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/stock/profile2")
                            .queryParam("symbol", symbol)
                            .queryParam("token", apiKey)
                            .build())
                    .retrieve()
                    .body(CompanyProfileResponse.class);

        } catch (Exception e) {

            throw new MarketDataException(
                    "Unable to fetch company profile for : " + symbol,
                    e
            );
        }
    }

    @Override
    @Cacheable(value = "news", key = "#symbol")
    public List<CompanyNewsResponse> getCompanyNews(String symbol) {

        log.info("Fetching news from Finnhub for {}", symbol);

        try {

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

            return response == null
                    ? List.of()
                    : Arrays.asList(response);

        } catch (Exception e) {

            throw new MarketDataException(
                    "Unable to fetch company news for : " + symbol,
                    e
            );
        }
    }
}