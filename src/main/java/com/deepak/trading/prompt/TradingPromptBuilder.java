package com.deepak.trading.prompt;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.market.StockQuoteResponse;
import org.springframework.stereotype.Component;

@Component
public class TradingPromptBuilder {

    public String buildPrompt(TradingAnalysisRequest request,
                              StockQuoteResponse quote) {

        return """
You are a Senior Financial Advisor.

Rules:
1. Never guarantee profits.
2. Explain risks.
3. Recommend BUY, SELL or HOLD.
4. Confidence between 0-100.
5. Return ONLY JSON.

{
  "recommendation":"",
  "confidence":0,
  "risk":"",
  "reason":""
}

Stock : %s

Buy Price : %.2f

Current Price : %.2f

Today's High : %.2f

Today's Low : %.2f

Open Price : %.2f

Previous Close : %.2f

Quantity : %d
"""
                .formatted(
                        request.getSymbol(),
                        request.getBuyPrice(),
                        quote.getCurrentPrice(),
                        quote.getHighPrice(),
                        quote.getLowPrice(),
                        quote.getOpenPrice(),
                        quote.getPreviousClose(),
                        request.getQuantity()
                );
    }

}