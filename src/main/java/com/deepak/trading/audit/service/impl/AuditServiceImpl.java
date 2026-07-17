package com.deepak.trading.audit.service.impl;

import com.deepak.trading.audit.dto.AuditResponse;
import com.deepak.trading.audit.entity.AuditLog;
import com.deepak.trading.audit.repository.AuditRepository;
import com.deepak.trading.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
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


        AuditLog auditLog
                =
                AuditLog.builder()
                        .userEmail(userEmail)
                        .action(action)
                        .status(status)
                        .entity(entity)
                        .details(details)
                        .ipAddress(ipAddress)
                        .createdAt(LocalDateTime.now())
                        .build();

        log.info("Audit Saved Successfully");

        repository.save(auditLog);
    }

    @Override
    public List<AuditResponse> getAllAudits() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private AuditResponse map(AuditLog audit) {

        return AuditResponse.builder()
                .id(audit.getId())
                .userEmail(audit.getUserEmail())
                .action(audit.getAction())
                .status(audit.getStatus())
                .entity(audit.getEntity())
                .details(audit.getDetails())
                .ipAddress(audit.getIpAddress())
                .createdAt(audit.getCreatedAt())
                .build();
    }
}