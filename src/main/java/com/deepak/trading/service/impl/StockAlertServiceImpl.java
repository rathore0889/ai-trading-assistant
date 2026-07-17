package com.deepak.trading.service.impl;

import com.deepak.trading.dto.alert.CreateAlertRequest;
import com.deepak.trading.dto.alert.StockAlertResponse;
import com.deepak.trading.entity.StockAlert;
import com.deepak.trading.entity.User;
import com.deepak.trading.event.StockAlertTriggeredEvent;
import com.deepak.trading.producer.TradingEventProducer;
import com.deepak.trading.repository.StockAlertRepository;
import com.deepak.trading.service.CurrentUserService;
import com.deepak.trading.service.MarketDataService;
import com.deepak.trading.service.StockAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockAlertServiceImpl implements StockAlertService {

    private final StockAlertRepository stockAlertRepository;
    private final CurrentUserService currentUserService;
    private final MarketDataService marketDataService;
    private final TradingEventProducer tradingEventProducer;

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

    @Override
    public List<StockAlert> findPendingAlerts() {

        return stockAlertRepository.findByTriggeredFalse();
    }

    @Override
    public void processAlerts() {

        List<StockAlert> alerts = findPendingAlerts();

        for (StockAlert alert : alerts) {

            var quote = marketDataService.getQuote(alert.getSymbol());

            double currentPrice = quote.getCurrentPrice();
            boolean shouldTrigger = false;

            if ("GREATER_THAN".equalsIgnoreCase(alert.getCondition())) {

                shouldTrigger =
                        currentPrice >= alert.getTargetPrice().doubleValue();

            } else if ("LESS_THAN".equalsIgnoreCase(alert.getCondition())) {

                shouldTrigger =
                        currentPrice <= alert.getTargetPrice().doubleValue();

            }

            if (shouldTrigger) {
                alert.setTriggered(true);
                stockAlertRepository.save(alert);

                StockAlertTriggeredEvent event =
                        StockAlertTriggeredEvent.builder()
                                .alertId(alert.getId())
                                .userId(alert.getUser().getId())
                                .userEmail(alert.getUser().getEmail())
                                .symbol(alert.getSymbol())
                                .currentPrice(
                                        java.math.BigDecimal.valueOf(currentPrice))
                                .targetPrice(alert.getTargetPrice())
                                .condition(alert.getCondition())
                                .build();

                tradingEventProducer.publishStockAlertTriggered(event);
            }
        }

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