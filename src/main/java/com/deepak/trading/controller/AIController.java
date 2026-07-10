package com.deepak.trading.controller;

import com.deepak.trading.dto.ChatRequest;
import com.deepak.trading.dto.ChatResponse;
import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;
import com.deepak.trading.dto.market.StockPriceResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;
import com.deepak.trading.entity.AnalysisHistory;
import com.deepak.trading.service.AIService;
import com.deepak.trading.service.MarketDataService;
import com.deepak.trading.service.TradingAnalysisService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;
    private final TradingAnalysisService tradingAnalysisService;
    private final MarketDataService marketDataService;

    public AIController(AIService aiService,
                        TradingAnalysisService tradingAnalysisService,
                        MarketDataService marketDataService) {

        this.aiService = aiService;
        this.tradingAnalysisService = tradingAnalysisService;
        this.marketDataService = marketDataService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {

        return aiService.chat(request);

    }

    @PostMapping("/analyze-stock")
    public ResponseEntity<TradingAnalysisResponse> analyzeStock(
            @Valid @RequestBody TradingAnalysisRequest request) {

        return ResponseEntity.ok(
                tradingAnalysisService.analyzeStock(request)
        );
    }

    @GetMapping("/history")
    public ResponseEntity<List<AnalysisHistory>> getHistory() {
        return ResponseEntity.ok(tradingAnalysisService.getAllHistory());
    }

    @GetMapping("/history/{symbol}")
    public ResponseEntity<List<AnalysisHistory>> getHistoryBySymbol(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                tradingAnalysisService.getHistoryBySymbol(symbol)
        );
    }

    @GetMapping("/price/{symbol}")
    public ResponseEntity<StockQuoteResponse> getPrice(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                marketDataService.getQuote(symbol)
        );
    }
}