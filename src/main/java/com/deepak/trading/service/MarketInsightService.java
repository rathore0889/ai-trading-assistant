package com.deepak.trading.service;

import com.deepak.trading.dto.market.MarketInsight;

public interface MarketInsightService {

    MarketInsight getMarketInsight(String symbol);

}