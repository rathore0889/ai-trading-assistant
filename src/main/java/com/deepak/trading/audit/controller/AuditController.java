package com.deepak.trading.audit.controller;

import com.deepak.trading.audit.dto.AuditResponse;
import com.deepak.trading.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping
    public List<AuditResponse> getAllAudits() {

        return auditService.getAllAudits();

    }

}