package com.example.probafeladat_backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Nyugta tétel")
public class RequestItem {

    @XmlElement(name = "megnevezes", required = true)
    @NotBlank(message = "Tétel megnevezés kötelező")
    @Schema(description = "Tétel megnevezés (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "Teszt termék")
    private String name;

    @XmlElement(name = "azonosito")
    @Schema(description = "Tétel azonosító (opcionális)", example = "PROD-001")
    private String itemId;

    @XmlElement(name = "mennyiseg", required = true)
    @NotNull(message = "Mennyiség kötelező")
    @Positive(message = "Mennyiség pozitív szám kell legyen")
    @Schema(description = "Mennyiség (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Double quantity;

    @XmlElement(name = "mennyisegiEgyseg", required = true)
    @NotBlank(message = "Mennyiségi egység kötelező")
    @Schema(description = "Mennyiségi egység (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "db")
    private String unit;

    @XmlElement(name = "nettoEgysegar", required = true)
    @NotNull(message = "Nettó egységár kötelező")
    @PositiveOrZero(message = "Nettó egységár nem lehet negatív")
    @Schema(description = "Nettó egységár (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "10000")
    private Double netUnitPrice;

    @XmlElement(name = "afakulcs", required = true)
    @NotBlank(message = "ÁFA kulcs kötelező")
    @Schema(description = "ÁFA kulcs (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private String vatRate;

    @XmlElement(name = "netto", required = true)
    @NotNull(message = "Nettó összeg kötelező")
    @PositiveOrZero(message = "Nettó összeg nem lehet negatív")
    @Schema(description = "Nettó összeg (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "10000")
    private Double netTotal;

    @XmlElement(name = "afa", required = true)
    @NotNull(message = "ÁFA összeg kötelező")
    @PositiveOrZero(message = "ÁFA összeg nem lehet negatív")
    @Schema(description = "ÁFA összeg (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    private Double vatAmount;

    @XmlElement(name = "brutto", required = true)
    @NotNull(message = "Bruttó összeg kötelező")
    @Positive(message = "Bruttó összeg pozitív szám kell legyen")
    @Schema(description = "Bruttó összeg (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "11000")
    private Double grossTotal;

    @XmlElement(name = "fokonyv")
    @Valid
    @Schema(description = "Főkönyvi adatok (opcionális)")
    private RequestItemLedger ledger;

    @XmlElement(name = "megjegyzes")
    @Schema(description = "Tétel megjegyzés (opcionális)")
    private String comment;

    @XmlElement(name = "torloKod")
    @Schema(description = "Törlő kód (opcionális)")
    private Integer deleteCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getNetUnitPrice() {
        return netUnitPrice;
    }

    public void setNetUnitPrice(Double netUnitPrice) {
        this.netUnitPrice = netUnitPrice;
    }

    public String getVatRate() {
        return vatRate;
    }

    public void setVatRate(String vatRate) {
        this.vatRate = vatRate;
    }

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    public Double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public Double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(Double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public RequestItemLedger getLedger() {
        return ledger;
    }

    public void setLedger(RequestItemLedger ledger) {
        this.ledger = ledger;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(Integer deleteCode) {
        this.deleteCode = deleteCode;
    }
}
