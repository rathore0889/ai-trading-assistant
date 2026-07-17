package com.deepak.trading.service;

import com.deepak.trading.entity.StockAlert;

import java.math.BigDecimal;

public interface AlertEventService {

    void publishAlertTriggeredEvent(StockAlert alert,
                                    BigDecimal currentPrice);

}