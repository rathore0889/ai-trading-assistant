package com.deepak.trading.repository;

import com.deepak.trading.entity.StockAlert;
import com.deepak.trading.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockAlertRepository
        extends JpaRepository<StockAlert, Long> {

    List<StockAlert> findByUser(User user);

    List<StockAlert> findByTriggeredFalse();

    List<StockAlert> findByTriggeredFalseAndUser(User user);
}