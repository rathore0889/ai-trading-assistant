package com.deepak.trading.service;

import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.StockPriceResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;

public interface MarketDataService {

    StockQuoteResponse getQuote(String symbol);

    CompanyProfileResponse getCompanyProfile(String symbol);

}