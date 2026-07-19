package com.deepak.trading.audit.repository;

import com.deepak.trading.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository
        extends JpaRepository<AuditLog, Long> {
}