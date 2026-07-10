package com.deepak.trading.service;

import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;

import java.util.List;

public interface MarketDataService {

    StockQuoteResponse getQuote(String symbol);

    CompanyProfileResponse getCompanyProfile(String symbol);

    List<CompanyNewsResponse> getCompanyNews(String symbol);
}