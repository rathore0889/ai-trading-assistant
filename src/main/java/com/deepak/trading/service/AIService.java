package com.deepak.trading.service;

import com.deepak.trading.dto.ChatRequest;
import com.deepak.trading.dto.ChatResponse;
import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;

public interface AIService {

    ChatResponse chat(ChatRequest request);
    TradingAnalysisResponse analyzeStock(TradingAnalysisRequest request);

}