package com.deepak.trading.service.impl;

import com.deepak.trading.dto.ChatRequest;
import com.deepak.trading.dto.ChatResponse;
import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;
import com.deepak.trading.prompt.TradingPromptBuilder;
import com.deepak.trading.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    private final ChatClient chatClient;
    private final TradingPromptBuilder tradingPromptBuilder;

    public AIServiceImpl(ChatClient.Builder builder,
                         TradingPromptBuilder tradingPromptBuilder) {

        this.chatClient = builder.build();
        this.tradingPromptBuilder = tradingPromptBuilder;
    }

    @Override
    public ChatResponse chat(ChatRequest request) {

        String response = chatClient
                .prompt(request.getMessage())
                .call()
                .content();

        return new ChatResponse(response);

    }

    @Override
    public TradingAnalysisResponse analyzeStock(TradingAnalysisRequest request) {

        String prompt = tradingPromptBuilder.buildPrompt(request);

        String aiResponse = chatClient
                .prompt(prompt)
                .call()
                .content();

        TradingAnalysisResponse response = new TradingAnalysisResponse();

        response.setRecommendation("TEMP");
        response.setConfidence(0);
        response.setRisk("NA");
        response.setReason(aiResponse);

        return response;
    }
}