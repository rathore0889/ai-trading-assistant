package com.deepak.trading.prompt;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.MarketInsight;
import com.deepak.trading.dto.market.StockQuoteResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradingPromptBuilder {

    public String buildPrompt(
            TradingAnalysisRequest request,
            MarketInsight insight) {

        StringBuilder newsBuilder = new StringBuilder();

        StockQuoteResponse quote = insight.getQuote();

        CompanyProfileResponse profile = insight.getProfile();

        List<CompanyNewsResponse> news = insight.getNews();

        news.stream()
                .limit(3)
                .forEach(item ->
                        newsBuilder.append("- ")
                                .append(item.getHeadline())
                                .append("\n"));

        return """
You are an expert financial advisor.

Analyze the stock.

Company: %s
Industry: %s
Exchange: %s

Current Price: %.2f
Buy Price: %.2f
Quantity: %d

Recent News:
%s

Respond ONLY with valid JSON.

{
  "recommendation":"BUY | SELL | HOLD",
  "confidence":0,
  "risk":"",
  "reason":""
}
"""
                .formatted(
                        profile.getName(),
                        profile.getFinnhubIndustry(),
                        profile.getExchange(),
                        quote.getCurrentPrice(),
                        request.getBuyPrice(),
                        request.getQuantity(),
                        newsBuilder
                );
    }

}