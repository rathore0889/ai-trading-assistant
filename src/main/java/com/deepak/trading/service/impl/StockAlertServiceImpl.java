package com.deepak.trading.service.impl;

import com.deepak.trading.dto.alert.CreateAlertRequest;
import com.deepak.trading.dto.alert.StockAlertResponse;
import com.deepak.trading.entity.StockAlert;
import com.deepak.trading.entity.User;
import com.deepak.trading.repository.StockAlertRepository;
import com.deepak.trading.service.CurrentUserService;
import com.deepak.trading.service.StockAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockAlertServiceImpl implements StockAlertService {

    private final StockAlertRepository stockAlertRepository;
    private final CurrentUserService currentUserService;

    @Override
    public StockAlertResponse createAlert(CreateAlertRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        StockAlert alert = StockAlert.builder()
                .symbol(request.getSymbol().toUpperCase())
                .targetPrice(request.getTargetPrice())
                .condition(request.getCondition())
                .triggered(false)
                .user(currentUser)
                .build();

        StockAlert savedAlert =
                stockAlertRepository.save(alert);

        return map(savedAlert);
    }

    @Override
    public List<StockAlertResponse> getMyAlerts() {

        User currentUser =
                currentUserService.getCurrentUser();

        return stockAlertRepository.findByUser(currentUser)
                .stream()
                .map(this::map)
                .toList();
    }

    private StockAlertResponse map(StockAlert alert) {

        return StockAlertResponse.builder()
                .id(alert.getId())
                .symbol(alert.getSymbol())
                .targetPrice(alert.getTargetPrice())
                .condition(alert.getCondition())
                .triggered(alert.isTriggered())
                .build();
    }
}