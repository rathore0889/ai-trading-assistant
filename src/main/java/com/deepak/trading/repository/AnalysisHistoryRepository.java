package com.deepak.trading.repository;

import com.deepak.trading.entity.AnalysisHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisHistoryRepository
        extends JpaRepository<AnalysisHistory, Long> {

}