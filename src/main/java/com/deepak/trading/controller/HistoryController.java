package com.deepak.trading.controller;

import com.deepak.trading.dto.response.AnalysisHistoryResponse;
import com.deepak.trading.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public List<AnalysisHistoryResponse> history() {

        return historyService.getHistory();
    }
}