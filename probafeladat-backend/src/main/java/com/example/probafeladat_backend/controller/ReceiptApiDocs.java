package com.example.probafeladat_backend.controller;

import com.example.probafeladat_backend.constants.SwaggerExamples;
import com.example.probafeladat_backend.dto.request.ReceiptRequest;
import com.example.probafeladat_backend.dto.response.ReceiptResponse;
import com.example.probafeladat_backend.dto.response.ReceiptSummaryDto;
import com.example.probafeladat_backend.entity.ReceiptEntity;
import com.example.probafeladat_backend.exception.ExternalApiErrorResponse;
import com.example.probafeladat_backend.exception.NotFoundErrorResponse;
import com.example.probafeladat_backend.exception.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * Swagger/OpenAPI dokumentáció a Receipt API-hoz.
 * A ReceiptController implementálja ezt az interface-t.
 */
@Tag(name = "Nyugta API", description = "Nyugták kezelése - létrehozás, listázás, részletek")
public interface ReceiptApiDocs {

    @Operation(
        summary = "Új nyugta létrehozása",
        description = "Új nyugta létrehozása a Számlázz.hu API-n keresztül. A nyugta adatai mentésre kerülnek a saját adatbázisban is."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nyugta sikeresen létrehozva",
            content = @Content(
                schema = @Schema(implementation = ReceiptResponse.class),
                examples = @ExampleObject(name = "Sikeres válasz", value = SwaggerExamples.RECEIPT_RESPONSE_SUCCESS)
            )),
        @ApiResponse(responseCode = "422", description = "Validációs hiba",
            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
        @ApiResponse(responseCode = "400", description = "Számlázz.hu API hiba",
            content = @Content(schema = @Schema(implementation = ExternalApiErrorResponse.class)))
    })
    ResponseEntity<ReceiptResponse> createReceipt(
        @RequestBody(
            description = "Nyugta adatai",
            required = true,
            content = @Content(
                schema = @Schema(implementation = ReceiptRequest.class),
                examples = @ExampleObject(name = "Minimális példa", value = SwaggerExamples.RECEIPT_REQUEST_MINIMAL)
            )
        )
        ReceiptRequest request
    ) throws JAXBException;

    @Operation(
        summary = "Teszt nyugta létrehozása",
        description = "Teszt nyugta létrehozása a https://docs.szamlazz.hu/hu/agent/generating_receipt/xml minta alapján"
    )
    @ApiResponse(responseCode = "200", description = "Teszt nyugta sikeresen létrehozva",
        content = @Content(
            schema = @Schema(implementation = ReceiptResponse.class),
            examples = @ExampleObject(name = "Teszt válasz", value = SwaggerExamples.RECEIPT_RESPONSE_SUCCESS)
        ))
    ResponseEntity<ReceiptResponse> testReceipt() throws IOException, JAXBException;

    @Operation(
        summary = "Összes nyugta listázása",
        description = "Az összes nyugta lekérdezése összefoglaló formátumban (id, nyugtaszám, dátum, nettó, bruttó)"
    )
    @ApiResponse(responseCode = "200", description = "Nyugták sikeresen lekérdezve",
        content = @Content(schema = @Schema(implementation = ReceiptSummaryDto.class)))
    ResponseEntity<List<ReceiptSummaryDto>> getAllReceipts();

    @Operation(
        summary = "Nyugta részletes adatai",
        description = "Egy nyugta teljes adatainak lekérdezése azonosító alapján (tételekkel, fizetésekkel, ÁFA összesítéssel)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nyugta sikeresen lekérdezve",
            content = @Content(
                schema = @Schema(implementation = ReceiptEntity.class),
                examples = @ExampleObject(name = "Sikeres válasz", value = SwaggerExamples.RECEIPT_RESPONSE_SUCCESS)
            )),
        @ApiResponse(responseCode = "404", description = "Nyugta nem található",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class)))
    })
    ResponseEntity<ReceiptEntity> getReceiptById(
        @Parameter(description = "Nyugta azonosítója", required = true) Long id
    );
}
