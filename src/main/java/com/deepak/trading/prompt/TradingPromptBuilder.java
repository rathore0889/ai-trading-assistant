package com.deepak.trading.prompt;

import com.deepak.trading.dto.TradingAnalysisRequest;
import org.springframework.stereotype.Component;

@Component
public class TradingPromptBuilder {

    public String buildPrompt(TradingAnalysisRequest request) {

        return """
You are a Senior Financial Advisor.

Rules:
- Never guarantee profits.
- Explain risks.
- Recommend only BUY, SELL or HOLD.
- Give confidence percentage.
- Return JSON only.

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