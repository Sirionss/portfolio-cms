package com.portfolio.portfolio_cms.exception;

import java.time.LocalDateTime;

public record ErrorResponce(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}
