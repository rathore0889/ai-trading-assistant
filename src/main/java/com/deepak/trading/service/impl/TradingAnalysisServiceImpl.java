package com.deepak.trading.service.impl;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;
import com.deepak.trading.prompt.TradingPromptBuilder;
import com.deepak.trading.service.TradingAnalysisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TradingAnalysisServiceImpl implements TradingAnalysisService {

    private final ChatClient chatClient;
    private final TradingPromptBuilder tradingPromptBuilder;
    private final ObjectMapper objectMapper;

    public TradingAnalysisServiceImpl(ChatClient.Builder builder,
                                      TradingPromptBuilder tradingPromptBuilder,
                                      ObjectMapper objectMapper) {

        this.chatClient = builder.build();
        this.tradingPromptBuilder = tradingPromptBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public TradingAnalysisResponse analyzeStock(TradingAnalysisRequest request) {

        String prompt = tradingPromptBuilder.buildPrompt(request);

        String aiResponse = chatClient
                .prompt(prompt)
                .call()
                .content();

        try {
            return objectMapper.readValue(
                    aiResponse,
                    TradingAnalysisResponse.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse AI response", e);
        }
    }
}