package com.deepak.trading.service.impl;

import com.deepak.trading.event.TradingAnalysisCompletedEvent;
import com.deepak.trading.service.WebSocketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketNotificationServiceImpl
        implements WebSocketNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendTradingUpdate(
            TradingAnalysisCompletedEvent event) {

        messagingTemplate.convertAndSend(
                "/topic/trading",
                event);

    }
}
