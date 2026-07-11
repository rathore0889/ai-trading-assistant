package com.deepak.trading.controller;

import com.deepak.trading.dto.ChatRequest;
import com.deepak.trading.dto.ChatResponse;
import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;
import com.deepak.trading.dto.market.CompanyNewsResponse;
import com.deepak.trading.dto.market.CompanyProfileResponse;
import com.deepak.trading.dto.market.StockQuoteResponse;
import com.deepak.trading.entity.AnalysisHistory;
import com.deepak.trading.service.AIService;
import com.deepak.trading.service.MarketDataService;
import com.deepak.trading.service.TradingAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "AI Trading",
        description = "AI Powered Trading APIs"
)
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

    @Operation(
            summary = "Analyze Stock",
            description = "AI analyzes the stock and returns BUY, HOLD or SELL recommendation"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Analysis Completed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "AI Processing Failed")
    })
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

    @GetMapping("/profile/{symbol}")
    public ResponseEntity<CompanyProfileResponse> profile(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                marketDataService.getCompanyProfile(symbol)
        );
    }

    @GetMapping("/news/{symbol}")
    public ResponseEntity<List<CompanyNewsResponse>> getNews(
            @PathVariable String symbol) {

        return ResponseEntity.ok(
                marketDataService.getCompanyNews(symbol)
        );
    }
}