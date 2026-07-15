package com.deepak.trading.mapper;

import com.deepak.trading.dto.response.AnalysisHistoryResponse;
import com.deepak.trading.entity.AnalysisHistory;
import org.springframework.stereotype.Component;

@Component
public class AnalysisHistoryMapper {

    public AnalysisHistoryResponse toResponse(
            AnalysisHistory history) {

        return new AnalysisHistoryResponse(

                history.getSymbol(),

                history.getBuyPrice(),

                history.getCurrentPrice(),

                history.getRecommendation(),

                history.getConfidence(),

                history.getRisk(),

                history.getReason(),

                history.getCreatedAt()
        );
    }
}