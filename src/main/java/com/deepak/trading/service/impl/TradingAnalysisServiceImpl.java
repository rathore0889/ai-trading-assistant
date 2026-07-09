package com.deepak.trading.service.impl;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.prompt.TradingPromptBuilder;
import com.deepak.trading.service.TradingAnalysisService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TradingAnalysisServiceImpl implements TradingAnalysisService {

    private final ChatClient chatClient;
    private final TradingPromptBuilder tradingPromptBuilder;

    public TradingAnalysisServiceImpl(ChatClient.Builder builder,
                                      TradingPromptBuilder tradingPromptBuilder) {
        this.chatClient = builder.build();
        this.tradingPromptBuilder = tradingPromptBuilder;
    }

    @Override
    public String analyzeStock(TradingAnalysisRequest request) {

        String prompt = tradingPromptBuilder.buildPrompt(request);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}