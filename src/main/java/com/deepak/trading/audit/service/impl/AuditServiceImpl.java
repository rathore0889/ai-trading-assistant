package com.deepak.trading.audit.service.impl;

import com.deepak.trading.audit.entity.AuditLog;
import com.deepak.trading.audit.repository.AuditRepository;
import com.deepak.trading.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl
        implements AuditService {

    private final AuditRepository repository;

    @Override
    public void saveAudit(
            String userEmail,
            String action,
            String status,
            String entity,
            String details,
            String ipAddress) {

        AuditLog log =
                AuditLog.builder()
                        .userEmail(userEmail)
                        .action(action)
                        .status(status)
                        .entity(entity)
                        .details(details)
                        .ipAddress(ipAddress)
                        .createdAt(LocalDateTime.now())
                        .build();

        repository.save(log);
    }
}