package com.deepak.trading.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, String> errors;
    private String path;
}