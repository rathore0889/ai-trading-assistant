package com.deepak.trading.service;

import com.deepak.trading.dto.alert.CreateAlertRequest;
import com.deepak.trading.dto.alert.StockAlertResponse;
import com.deepak.trading.entity.StockAlert;

import java.util.List;

public interface StockAlertService {

    StockAlertResponse createAlert(CreateAlertRequest request);

    List<StockAlertResponse> getMyAlerts();

    List<StockAlert> findPendingAlerts();

    void processAlerts();
}