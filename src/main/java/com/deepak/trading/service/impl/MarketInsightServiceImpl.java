package com.deepak.trading.service.impl;

import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.MarketInsight;
import com.deepak.trading.dto.market.StockQuoteResponse;
import com.deepak.trading.service.MarketDataService;
import com.deepak.trading.service.MarketInsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MarketInsightServiceImpl
        implements MarketInsightService {

    private final MarketDataService marketDataService;

    @Override
    public MarketInsight getMarketInsight(String symbol) {

        CompletableFuture<StockQuoteResponse> quoteFuture =
                marketDataService.getQuoteAsync(symbol);

        CompletableFuture<CompanyProfileResponse> profileFuture =
                marketDataService.getCompanyProfileAsync(symbol);

        CompletableFuture<List<CompanyNewsResponse>> newsFuture =
                marketDataService.getCompanyNewsAsync(symbol);

        CompletableFuture.allOf(
                quoteFuture,
                profileFuture,
                newsFuture
        ).join();

        MarketInsight insight = new MarketInsight();

        insight.setQuote(quoteFuture.join());

        insight.setProfile(profileFuture.join());

        insight.setNews(newsFuture.join());

        return insight;
    }
}
