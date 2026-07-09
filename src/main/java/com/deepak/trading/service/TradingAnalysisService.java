package com.deepak.trading.service;

import com.deepak.trading.dto.TradingAnalysisRequest;
import com.deepak.trading.dto.TradingAnalysisResponse;
import com.deepak.trading.entity.AnalysisHistory;

import java.util.List;

public interface TradingAnalysisService {

    TradingAnalysisResponse analyzeStock(TradingAnalysisRequest request);
    List<AnalysisHistory> getAllHistory();

    List<AnalysisHistory> getHistoryBySymbol(String symbol);

}