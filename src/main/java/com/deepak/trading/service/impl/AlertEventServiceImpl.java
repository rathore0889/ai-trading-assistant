package com.deepak.trading.service.impl;

import com.deepak.trading.entity.StockAlert;
import com.deepak.trading.producer.TradingEventProducer;
import com.deepak.trading.service.AlertEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AlertEventServiceImpl
        implements AlertEventService {

    private final TradingEventProducer producer;

    @Override
    public void publishAlertTriggeredEvent(
            StockAlert alert,
            BigDecimal currentPrice) {

        // TODO
    }
}
