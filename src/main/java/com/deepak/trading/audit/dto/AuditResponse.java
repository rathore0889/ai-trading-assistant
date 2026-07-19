package com.deepak.trading.audit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuditResponse {

    private Long id;

    private String userEmail;

    private String action;

    private String status;

    private String entity;

    private String details;

    private String ipAddress;

    private LocalDateTime createdAt;
}