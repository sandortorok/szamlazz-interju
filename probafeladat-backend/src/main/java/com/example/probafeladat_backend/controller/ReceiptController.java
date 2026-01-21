package com.example.probafeladat_backend.controller;

import com.example.probafeladat_backend.dto.request.ReceiptRequest;
import com.example.probafeladat_backend.dto.response.ReceiptResponse;
import com.example.probafeladat_backend.dto.response.ReceiptSummaryDto;
import com.example.probafeladat_backend.entity.ReceiptEntity;
import com.example.probafeladat_backend.services.ReceiptService;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController implements ReceiptApiDocs {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ReceiptResponse> createReceipt(@Valid @RequestBody ReceiptRequest request) throws JAXBException {
        return ResponseEntity.ok(receiptService.createReceipt(request));
    }

    @Override
    @GetMapping("/test")
    public ResponseEntity<ReceiptResponse> testReceipt() throws IOException, JAXBException {
        return ResponseEntity.ok(receiptService.testWithSample());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ReceiptSummaryDto>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ReceiptEntity> getReceiptById(@PathVariable Long id) {
        return ResponseEntity.ok(receiptService.getReceiptById(id));
    }
}
