package com.deepak.trading.audit.service;

public interface AuditService {

    void saveAudit(
            String userEmail,
            String action,
            String status,
            String entity,
            String details,
            String ipAddress);

}