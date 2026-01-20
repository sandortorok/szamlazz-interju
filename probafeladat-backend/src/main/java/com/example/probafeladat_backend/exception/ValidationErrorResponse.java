package com.example.probafeladat_backend.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Validációs hibaválasz")
public class ValidationErrorResponse extends BaseErrorResponse {

    @Schema(description = "Validációs hibák listája", example = "[\"prefix: nem lehet üres\", \"paymentMethod: nem lehet üres\"]")
    private List<String> errors;

    public ValidationErrorResponse() {
        super();
        this.errors = new ArrayList<>();
    }

    public ValidationErrorResponse(String message, List<String> errors, String path) {
        super(422, message, path);
        this.errors = errors;
    }

    @Override
    @Schema(description = "HTTP státuszkód", example = "422")
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    @Schema(description = "Hibaüzenet", example = "Validációs hibák")
    public String getMessage() {
        return super.getMessage();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
}
