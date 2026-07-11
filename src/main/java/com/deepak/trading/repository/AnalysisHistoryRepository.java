package com.deepak.trading.repository;

import com.deepak.trading.entity.AnalysisHistory;
import com.deepak.trading.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisHistoryRepository extends JpaRepository<AnalysisHistory, Long> {

    List<AnalysisHistory> findBySymbolIgnoreCase(String symbol);
    List<AnalysisHistory> findByUserOrderByCreatedAtDesc(User user);

}