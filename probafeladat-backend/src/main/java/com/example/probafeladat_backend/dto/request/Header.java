package com.example.probafeladat_backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Nyugta fejléc adatok")
public class Header {

    @XmlElement(name = "hivasAzonosito")
    @Schema(description = "Egyedi hívás azonosító (opcionális)", example = "CALL-12345")
    private String callId;

    @XmlElement(name = "elotag", required = true)
    @NotBlank(message = "Előtag kötelező")
    @Schema(description = "Nyugta előtag (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "PQRST")
    private String prefix;

    @XmlElement(name = "fizmod", required = true)
    @NotBlank(message = "Fizetési mód kötelező")
    @Schema(description = "Fizetési mód (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "készpénz")
    private String paymentMethod;

    @XmlElement(name = "penznem", required = true)
    @NotBlank(message = "Pénznem kötelező")
    @Schema(description = "Pénznem (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "Ft")
    private String currency;

    @XmlElement(name = "devizaarf")
    @Positive(message = "Árfolyam pozitív szám kell legyen")
    @Schema(description = "Deviza árfolyam (opcionális)", example = "380.5")
    private Double exchangeRate;

    @XmlElement(name = "devizabank")
    @Schema(description = "Deviza bank neve (opcionális)", example = "MNB")
    private String exchangeBank;

    @XmlElement(name = "megjegyzes")
    @Schema(description = "Megjegyzés (opcionális)", example = "Teszt nyugta")
    private String comment;

    @XmlElement(name = "pdfSablon")
    @Schema(description = "PDF sablon neve (opcionális)", example = "")
    private String pdfTemplate;

    @XmlElement(name = "fokonyvVevo")
    @Schema(description = "Főkönyvi vevő (opcionális)", example = "")
    private String ledgerCustomer;

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeBank() {
        return exchangeBank;
    }

    public void setExchangeBank(String exchangeBank) {
        this.exchangeBank = exchangeBank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPdfTemplate() {
        return pdfTemplate;
    }

    public void setPdfTemplate(String pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
    }

    public String getLedgerCustomer() {
        return ledgerCustomer;
    }

    public void setLedgerCustomer(String ledgerCustomer) {
        this.ledgerCustomer = ledgerCustomer;
    }
}
