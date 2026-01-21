package com.example.probafeladat_backend.controller;

import com.example.probafeladat_backend.dto.request.ReceiptRequest;
import com.example.probafeladat_backend.dto.response.ReceiptResponse;
import com.example.probafeladat_backend.dto.response.ReceiptSummaryDto;
import com.example.probafeladat_backend.entity.ReceiptEntity;
import com.example.probafeladat_backend.exception.ExternalApiErrorResponse;
import com.example.probafeladat_backend.exception.NotFoundErrorResponse;
import com.example.probafeladat_backend.exception.ValidationErrorResponse;
import com.example.probafeladat_backend.services.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@Tag(name = "Nyugta API", description = "Nyugták kezelése - létrehozás, listázás, részletek")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final JAXBContext jaxbContext;

    public ReceiptController(ReceiptService receiptService) throws JAXBException {
        this.receiptService = receiptService;
        this.jaxbContext = JAXBContext.newInstance(ReceiptRequest.class);
    }

    @PostMapping
    @Operation(summary = "Új nyugta létrehozása", description = "Új nyugta létrehozása a Számlázz.hu API-n keresztül. A nyugta adatai mentésre kerülnek a saját adatbázisban is.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nyugta sikeresen létrehozva", content = @Content(schema = @Schema(implementation = ReceiptResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validációs hiba", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Számlázz.hu API hiba", content = @Content(schema = @Schema(implementation = ExternalApiErrorResponse.class)))
    })
    public ResponseEntity<?> createReceipt(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Nyugta adatai", required = true, content = @Content(schema = @Schema(implementation = ReceiptRequest.class))) @Valid @RequestBody ReceiptRequest request) {
        try {
            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(request, writer);
            String xmlContent = writer.toString();

            ReceiptResponse response = receiptService.createReceipt(xmlContent);

            if (!response.isSuccess()) {
                ExternalApiErrorResponse error = new ExternalApiErrorResponse(
                        response.getErrorMessage(),
                        String.valueOf(response.getErrorCode()),
                        "/api/receipts");
                return ResponseEntity.badRequest().body(error);
            }

            return ResponseEntity.ok(response);
        } catch (JAXBException e) {
            ValidationErrorResponse error = new ValidationErrorResponse(
                    "XML feldolgozási hiba",
                    List.of(e.getMessage()),
                    "/api/receipts");
            return ResponseEntity.status(422).body(error);
        }
    }

    @GetMapping("/test")
    @Operation(summary = "Teszt nyugta létrehozása", description = "Teszt nyugta létrehozása a resources/samples/receipt-sample.xml minta alapján")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teszt nyugta sikeresen létrehozva", content = @Content(schema = @Schema(implementation = ReceiptResponse.class)))
    })
    public ResponseEntity<ReceiptResponse> testReceipt() throws IOException, JAXBException {
        ReceiptResponse response = receiptService.testWithSample();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Összes nyugta listázása", description = "Az összes nyugta lekérdezése összefoglaló formátumban (id, nyugtaszám, dátum, nettó, bruttó)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nyugták sikeresen lekérdezve", content = @Content(schema = @Schema(implementation = ReceiptSummaryDto.class)))
    })
    public ResponseEntity<List<ReceiptSummaryDto>> getAllReceipts() {
        List<ReceiptSummaryDto> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Nyugta részletes adatai", description = "Egy nyugta teljes adatainak lekérdezése azonosító alapján (tételekkel, fizetésekkel, ÁFA összesítéssel)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nyugta sikeresen lekérdezve", content = @Content(schema = @Schema(implementation = ReceiptEntity.class))),
            @ApiResponse(responseCode = "404", description = "Nyugta nem található", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class)))
    })
    public ResponseEntity<?> getReceiptById(
            @Parameter(description = "Nyugta azonosítója", required = true) @PathVariable Long id) {
        return receiptService.getReceiptById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> {
                    NotFoundErrorResponse error = new NotFoundErrorResponse(
                            "Receipt not found with id: " + id,
                            "/api/receipts/" + id);
                    return ResponseEntity.status(404).body(error);
                });
    }
}
