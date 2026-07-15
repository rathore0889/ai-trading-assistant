package com.deepak.trading.service;

import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MarketDataService {

    StockQuoteResponse getQuote(String symbol);

    CompanyProfileResponse getCompanyProfile(String symbol);

    List<CompanyNewsResponse> getCompanyNews(String symbol);

    CompletableFuture<StockQuoteResponse> getQuoteAsync(String symbol);

    CompletableFuture<CompanyProfileResponse> getCompanyProfileAsync(String symbol);

    CompletableFuture<List<CompanyNewsResponse>> getCompanyNewsAsync(String symbol);
}