package com.deepak.trading.service.impl;

import com.deepak.trading.audit.service.AuditService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockAlertServiceImpl implements StockAlertService {

    private final StockAlertRepository stockAlertRepository;
    private final CurrentUserService currentUserService;
    private final MarketDataService marketDataService;
    private final TradingEventProducer tradingEventProducer;
    private final AuditService auditService;

    @Override
    public StockAlertResponse createAlert(CreateAlertRequest request) {

        log.info("========== CREATE ALERT START ==========");

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

        log.info("Stock Alert Saved Successfully");
        log.info("Saving Audit...");

        auditService.saveAudit(
                currentUser.getEmail(),
                "CREATE_ALERT",
                "SUCCESS",
                "StockAlert",
                "Created Alert : " + savedAlert.getSymbol()
                        + " "
                        + savedAlert.getCondition()
                        + " "
                        + savedAlert.getTargetPrice(),
                "SYSTEM"
        );

        log.info("Audit Service Called");

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

                auditService.saveAudit(
                        alert.getUser().getEmail(),
                        "TRIGGER_ALERT",
                        "SUCCESS",
                        "StockAlert",
                        "Alert Triggered : "
                                + alert.getSymbol()
                                + " Current Price="
                                + currentPrice,
                        "SYSTEM"
                );

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