package com.example.probafeladat_backend.exception;

public class ReceiptNotFoundException extends RuntimeException {
    private final Long id;

    public ReceiptNotFoundException(Long id) {
        super("Nyugta nem található ezzel az azonosítóval: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
