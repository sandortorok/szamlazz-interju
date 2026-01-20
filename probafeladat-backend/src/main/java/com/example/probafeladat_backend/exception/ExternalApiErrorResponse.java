package com.example.probafeladat_backend.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Külső API hibaválasz (pl. Számlázz.hu)")
public class ExternalApiErrorResponse extends BaseErrorResponse {

    @Schema(description = "Külső API hibakódja", example = "57")
    private String errorCode;

    public ExternalApiErrorResponse() {
        super();
    }

    public ExternalApiErrorResponse(String message, String errorCode, String path) {
        super(400, message, path);
        this.errorCode = errorCode;
    }

    @Override
    @Schema(description = "HTTP státuszkód", example = "400")
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    @Schema(description = "Hibaüzenet", example = "Számlázz.hu API hiba: Hibás API kulcs")
    public String getMessage() {
        return super.getMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
