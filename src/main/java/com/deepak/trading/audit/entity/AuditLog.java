package com.deepak.trading.audit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String action;

    private String status;

    private String entity;

    @Column(length = 2000)
    private String details;

    private String ipAddress;

    private LocalDateTime createdAt;
}