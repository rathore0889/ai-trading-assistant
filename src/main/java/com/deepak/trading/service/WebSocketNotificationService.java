package com.deepak.trading.service;

import com.deepak.trading.event.StockAlertTriggeredEvent;
import com.deepak.trading.event.TradingAnalysisCompletedEvent;

public interface WebSocketNotificationService {

    void sendTradingUpdate(
            TradingAnalysisCompletedEvent event);

    void sendStockAlert(
            StockAlertTriggeredEvent event);

}