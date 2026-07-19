package com.deepak.trading.notification;

import com.deepak.trading.event.StockAlertTriggeredEvent;
import com.deepak.trading.event.TradingAnalysisCompletedEvent;

public interface EmailService {

    void sendTradingAnalysisEmail(
            TradingAnalysisCompletedEvent event);

    void sendStockAlertEmail(StockAlertTriggeredEvent event);

}