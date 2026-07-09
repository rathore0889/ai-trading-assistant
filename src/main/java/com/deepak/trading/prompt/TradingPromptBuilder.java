package com.deepak.trading.prompt;

import com.deepak.trading.dto.TradingAnalysisRequest;
import org.springframework.stereotype.Component;

@Component
public class TradingPromptBuilder {

    public String buildPrompt(TradingAnalysisRequest request) {

        return """
You are a Senior Financial Advisor.

Rules:
1. Never guarantee profits.
2. Explain risks.
3. Recommend only BUY, SELL or HOLD.
4. Confidence should be between 0-100.
5. Return ONLY valid JSON.
6. Do NOT add markdown.
7. Do NOT write explanation outside JSON.

Return exactly this structure:

{
  "recommendation":"",
  "confidence":0,
  "risk":"",
  "reason":""
}

Stock : %s

Buy Price : %.2f

Current Price : %.2f

Quantity : %d

"""
                .formatted(
                        request.getSymbol(),
                        request.getBuyPrice(),
                        request.getCurrentPrice(),
                        request.getQuantity());
    }

}