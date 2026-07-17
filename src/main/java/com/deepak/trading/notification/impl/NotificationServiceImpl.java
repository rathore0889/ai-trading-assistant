package com.deepak.trading.notification.impl;

import com.deepak.trading.event.StockAlertTriggeredEvent;
import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import com.deepak.trading.notification.EmailService;
import com.deepak.trading.notification.NotificationService;
import com.deepak.trading.service.WebSocketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final EmailService emailService;
    private final WebSocketNotificationService webSocketNotificationService;

    @Override
    public void notifyUser(TradingAnalysisCompletedEvent event) {

        // Email
        emailService.sendTradingAnalysisEmail(event);

        // WebSocket
        webSocketNotificationService.sendTradingUpdate(event);
    }

    @Override
    public void notifyAlert(
            StockAlertTriggeredEvent event) {

        emailService.sendStockAlertEmail(event);

        webSocketNotificationService.sendStockAlert(event);

    }
}