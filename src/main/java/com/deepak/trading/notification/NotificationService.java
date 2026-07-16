package com.deepak.trading.notification;

import com.deepak.trading.event.TradingAnalysisCompletedEvent;

public interface NotificationService {

    void notifyUser(TradingAnalysisCompletedEvent event);

}