package com.deepak.trading.service;

import com.deepak.trading.dto.response.AnalysisHistoryResponse;

import java.util.List;

public interface HistoryService {

    List<AnalysisHistoryResponse> getHistory();
}
