package com.deepak.trading.service.impl;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;
import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.MarketInsight;
import com.deepak.trading.dto.market.StockQuoteResponse;
import com.deepak.trading.entity.AnalysisHistory;
import com.deepak.trading.exception.AIResponseParsingException;
import com.deepak.trading.prompt.TradingPromptBuilder;
import com.deepak.trading.repository.AnalysisHistoryRepository;
import com.deepak.trading.service.MarketDataService;
import com.deepak.trading.service.MarketInsightService;
import com.deepak.trading.service.TradingAnalysisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradingAnalysisServiceImpl implements TradingAnalysisService {

    private final ChatClient chatClient;
    private final TradingPromptBuilder tradingPromptBuilder;
    private final ObjectMapper objectMapper;
    private final AnalysisHistoryRepository repository;
    private final MarketInsightService marketInsightService;

    public TradingAnalysisServiceImpl(ChatClient.Builder builder,
                                      TradingPromptBuilder tradingPromptBuilder,
                                      ObjectMapper objectMapper,
                                      AnalysisHistoryRepository repository,
                                      MarketInsightService marketInsightService) {

        this.chatClient = builder.build();
        this.tradingPromptBuilder = tradingPromptBuilder;
        this.objectMapper = objectMapper;
        this.repository = repository;
        this.marketInsightService = marketInsightService;
    }

    @Override
    public TradingAnalysisResponse analyzeStock(TradingAnalysisRequest request) {

        long start = System.currentTimeMillis();
        long marketStart = System.currentTimeMillis();

        MarketInsight insight =
                marketInsightService.getMarketInsight(request.getSymbol());

        StockQuoteResponse quote = insight.getQuote();

        System.out.println("Market API Time : "
                + (System.currentTimeMillis() - marketStart));

        long promptStart = System.currentTimeMillis();

        String prompt = tradingPromptBuilder.buildPrompt(
                request,
                insight
        );

        System.out.println("Prompt Time : "
                + (System.currentTimeMillis() - promptStart));
        long aiStart = System.currentTimeMillis();

        try {
        String aiResponse = chatClient
                .prompt(prompt)
                .call()
                .content();

        System.out.println("========== AI RAW RESPONSE ==========");
        System.out.println(aiResponse);
        System.out.println("=====================================");

        TradingAnalysisResponse response =
                objectMapper.readValue(
                        aiResponse,
                        TradingAnalysisResponse.class
                );

        System.out.println("AI Time : "
                + (System.currentTimeMillis() - aiStart));

        System.out.println("Total Time : "
                + (System.currentTimeMillis() - start));

            // DTO -> Entity
            AnalysisHistory history = new AnalysisHistory();

            history.setSymbol(request.getSymbol());
            history.setBuyPrice(request.getBuyPrice());
            history.setCurrentPrice(quote.getCurrentPrice());
            history.setQuantity(request.getQuantity());

            history.setRecommendation(response.getRecommendation());
            history.setConfidence(response.getConfidence());
            history.setRisk(response.getRisk());
            history.setReason(response.getReason());

            history.setCreatedAt(LocalDateTime.now());

            // Save into PostgreSQL
            repository.save(history);

            return response;
        } catch (Exception e) {
//            log.error("Unable to parse AI response", e);
            throw new AIResponseParsingException("Invalid AI response received from Ollama",e);
        }

    }

    @Override
    public List<AnalysisHistory> getAllHistory() {
        return repository.findAll();
    }

    @Override
    public List<AnalysisHistory> getHistoryBySymbol(String symbol) {
        return repository.findBySymbolIgnoreCase(symbol);
    }
}