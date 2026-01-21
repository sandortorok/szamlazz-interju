package com.example.probafeladat_backend.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Alap hibaválasz")
public abstract class BaseErrorResponse {

    @Schema(description = "HTTP státuszkód", example = "400")
    private int status;

    @Schema(description = "Hibaüzenet", example = "Hiba történt")
    private String message;

    @Schema(description = "Hiba időpontja", example = "2026-01-20T22:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "Kérés útvonala", example = "/api/receipts")
    private String path;

    public BaseErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public BaseErrorResponse(int status, String message, String path) {
        this();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
