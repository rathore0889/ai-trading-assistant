package com.deepak.trading.service;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;

public interface TradingAnalysisService {

    TradingAnalysisResponse analyzeStock(TradingAnalysisRequest request);

}