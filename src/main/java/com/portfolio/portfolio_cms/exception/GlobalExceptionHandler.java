package com.portfolio.portfolio_cms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.DrbgParameters;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponce> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponce errorResponce = new ErrorResponce(LocalDateTime.now(), 404, "Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponce);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponce> handleGeneric(Exception ex){
        ErrorResponce errorResponce = new ErrorResponce(LocalDateTime.now(), 500, "Internal Server Error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponce);
    }
}
