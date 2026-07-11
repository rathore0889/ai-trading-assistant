package com.deepak.trading.service.impl;

import com.deepak.trading.dto.response.AnalysisHistoryResponse;
import com.deepak.trading.entity.User;
import com.deepak.trading.mapper.AnalysisHistoryMapper;
import com.deepak.trading.repository.AnalysisHistoryRepository;
import com.deepak.trading.service.CurrentUserService;
import com.deepak.trading.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl
        implements HistoryService {

    private final AnalysisHistoryRepository repository;

    private final CurrentUserService currentUserService;

    private final AnalysisHistoryMapper mapper;

    @Override
    public List<AnalysisHistoryResponse> getHistory() {

        User user = currentUserService.getCurrentUser();

        return repository
                .findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
