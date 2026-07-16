package com.deepak.trading.notification.impl;

import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import com.deepak.trading.notification.EmailService;
import com.deepak.trading.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final EmailService emailService;

    @Override
    public void notifyUser(TradingAnalysisCompletedEvent event) {

        emailService.sendTradingAnalysisEmail(event);
    }
}
