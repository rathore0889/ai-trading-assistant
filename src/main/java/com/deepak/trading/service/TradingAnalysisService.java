package com.deepak.trading.service;

import com.deepak.trading.dto.TradingAnalysisRequest;

public interface TradingAnalysisService {

    String analyzeStock(TradingAnalysisRequest request);

}