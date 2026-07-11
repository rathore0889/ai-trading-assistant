package com.deepak.trading.controller;

import com.deepak.trading.dto.response.AnalysisHistoryResponse;
import com.deepak.trading.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "History",
        description = "User Analysis History APIs"
)
@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @Operation(
            summary = "Get Analysis History",
            description = "Returns analysis history for the currently authenticated user"
    )
    @GetMapping
    public List<AnalysisHistoryResponse> history() {

        return historyService.getHistory();
    }
}