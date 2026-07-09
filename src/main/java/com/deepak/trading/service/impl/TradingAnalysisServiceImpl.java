package com.deepak.trading.service.impl;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;
import com.deepak.trading.dto.market.StockPriceResponse;
import com.deepak.trading.entity.AnalysisHistory;
import com.deepak.trading.prompt.TradingPromptBuilder;
import com.deepak.trading.repository.AnalysisHistoryRepository;
import com.deepak.trading.service.MarketDataService;
import com.deepak.trading.service.TradingAnalysisService;
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
    private final MarketDataService marketDataService;

    public TradingAnalysisServiceImpl(ChatClient.Builder builder,
                                      TradingPromptBuilder tradingPromptBuilder,
                                      ObjectMapper objectMapper,
                                      AnalysisHistoryRepository repository,
                                      MarketDataService marketDataService) {

        this.chatClient = builder.build();
        this.tradingPromptBuilder = tradingPromptBuilder;
        this.objectMapper = objectMapper;
        this.repository = repository;
        this.marketDataService = marketDataService;
    }

    @Override
    public TradingAnalysisResponse analyzeStock(TradingAnalysisRequest request) {

        StockPriceResponse stockPrice =
                marketDataService.getCurrentPrice(request.getSymbol());

        Double currentPrice = stockPrice.getCurrentPrice();

        String prompt = tradingPromptBuilder.buildPrompt(request, currentPrice);

        String aiResponse = chatClient
                .prompt(prompt)
                .call()
                .content();

        try {

            // JSON -> DTO
            TradingAnalysisResponse response =
                    objectMapper.readValue(aiResponse, TradingAnalysisResponse.class);

            // DTO -> Entity
            AnalysisHistory history = new AnalysisHistory();

            history.setSymbol(request.getSymbol());
            history.setBuyPrice(request.getBuyPrice());
            history.setCurrentPrice(currentPrice);
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
            throw new RuntimeException("Unable to parse AI response", e);
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