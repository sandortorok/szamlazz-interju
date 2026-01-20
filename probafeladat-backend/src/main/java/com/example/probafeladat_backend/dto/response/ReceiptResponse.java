package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmlnyugtavalasz")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReceiptResponse {

    @XmlElement(name = "sikeres", required = true)
    private boolean success;

    @XmlElement(name = "hibakod")
    private Integer errorCode;

    @XmlElement(name = "hibauzenet")
    private String errorMessage;

    @XmlElement(name = "nyugtaPdf")
    private String receiptPdf;

    @XmlElement(name = "nyugta")
    private Receipt receipt;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReceiptPdf() {
        return receiptPdf;
    }

    public void setReceiptPdf(String receiptPdf) {
        this.receiptPdf = receiptPdf;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
