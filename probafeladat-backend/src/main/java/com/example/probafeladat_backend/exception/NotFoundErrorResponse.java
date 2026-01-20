package com.example.probafeladat_backend.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Nem található hibaválasz")
public class NotFoundErrorResponse extends BaseErrorResponse {

    public NotFoundErrorResponse() {
        super();
    }

    public NotFoundErrorResponse(String message, String path) {
        super(404, message, path);
    }

    @Override
    @Schema(description = "HTTP státuszkód", example = "404")
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    @Schema(description = "Hibaüzenet", example = "Receipt not found with id: 123")
    public String getMessage() {
        return super.getMessage();
    }
}
