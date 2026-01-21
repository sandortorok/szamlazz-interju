package com.example.probafeladat_backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    if (error instanceof FieldError fieldError) {
                        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .toList();

        return ResponseEntity.status(422).body(
                new ValidationErrorResponse("Validációs hiba", errors, request.getRequestURI()));
    }

    @ExceptionHandler(JAXBException.class)
    public ResponseEntity<ValidationErrorResponse> handleJAXBException(
            JAXBException ex, HttpServletRequest request) {
        
        return ResponseEntity.status(422).body(
                new ValidationErrorResponse("XML feldolgozási hiba", List.of(ex.getMessage()), request.getRequestURI()));
    }

    @ExceptionHandler(ReceiptNotFoundException.class)
    public ResponseEntity<NotFoundErrorResponse> handleReceiptNotFound(
            ReceiptNotFoundException ex, HttpServletRequest request) {
        
        return ResponseEntity.status(404).body(
                new NotFoundErrorResponse(ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ExternalApiErrorResponse> handleExternalApiException(
            ExternalApiException ex, HttpServletRequest request) {
        
        return ResponseEntity.badRequest().body(
                new ExternalApiErrorResponse(ex.getMessage(), ex.getErrorCode(), request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExternalApiErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {
        
        return ResponseEntity.status(500).body(
                new ExternalApiErrorResponse("Belső szerverhiba: " + ex.getMessage(), "500", request.getRequestURI()));
    }
}
