package com.deepak.trading.service.impl;

import com.deepak.trading.dto.market.MarketInsight;
import com.deepak.trading.service.MarketDataService;
import com.deepak.trading.service.MarketInsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketInsightServiceImpl
        implements MarketInsightService {

    private final MarketDataService marketDataService;

    @Override
    public MarketInsight getMarketInsight(String symbol) {

        MarketInsight insight = new MarketInsight();

        insight.setQuote(
                marketDataService.getQuote(symbol)
        );

        insight.setProfile(
                marketDataService.getCompanyProfile(symbol)
        );

        insight.setNews(
                marketDataService.getCompanyNews(symbol)
        );

        return insight;
    }

}
