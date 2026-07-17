package com.deepak.trading.scheduler;

import com.deepak.trading.service.StockAlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockAlertScheduler {

    private final StockAlertService stockAlertService;

    @Scheduled(fixedRate = 30000)
    public void checkAlerts() {

        log.info("Checking Pending Stock Alerts...");

        stockAlertService.processAlerts();

    }
}