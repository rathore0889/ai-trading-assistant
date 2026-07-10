package com.deepak.trading.mapper;

import com.deepak.trading.dto.market.FinnhubQuoteResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockQuoteResponse toResponse(FinnhubQuoteResponse quote) {
        return StockQuoteResponse.builder()
                .currentPrice(quote.getCurrentPrice())
                .highPrice(quote.getHighPrice())
                .lowPrice(quote.getLowPrice())
                .openPrice(quote.getOpenPrice())
                .previousClose(quote.getPreviousClose())
                .build();
    }
}
