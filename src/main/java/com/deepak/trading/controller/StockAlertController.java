package com.deepak.trading.controller;

import com.deepak.trading.dto.alert.CreateAlertRequest;
import com.deepak.trading.dto.alert.StockAlertResponse;
import com.deepak.trading.service.StockAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class StockAlertController {

    private final StockAlertService stockAlertService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockAlertResponse createAlert(
            @Valid @RequestBody CreateAlertRequest request) {

        return stockAlertService.createAlert(request);
    }

    @GetMapping
    public List<StockAlertResponse> getMyAlerts() {

        return stockAlertService.getMyAlerts();
    }
}