package com.deepak.trading.service;

import com.deepak.trading.dto.alert.CreateAlertRequest;
import com.deepak.trading.dto.alert.StockAlertResponse;

import java.util.List;

public interface StockAlertService {

    StockAlertResponse createAlert(CreateAlertRequest request);

    List<StockAlertResponse> getMyAlerts();
}