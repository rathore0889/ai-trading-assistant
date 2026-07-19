package com.deepak.trading.audit.service;

import com.deepak.trading.audit.dto.AuditResponse;

import java.util.List;

public interface AuditService {

    void saveAudit(
            String userEmail,
            String action,
            String status,
            String entity,
            String details,
            String ipAddress);

    List<AuditResponse> getAllAudits();

}