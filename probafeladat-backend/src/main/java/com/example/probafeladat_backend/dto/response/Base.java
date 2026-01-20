package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Base {

    @XmlElement(name = "id", required = true)
    private Integer id;

    @XmlElement(name = "hivasAzonosito")
    private String callId;

    @XmlElement(name = "nyugtaszam", required = true)
    private String receiptNumber;

    @XmlElement(name = "tipus", required = true)
    private String type;

    @XmlElement(name = "stornozott", required = true)
    private boolean cancelled;

    @XmlElement(name = "stornozottNyugtaszam")
    private String cancelledReceiptNumber;

    @XmlElement(name = "kelt", required = true)
    private String date;

    @XmlElement(name = "fizmod", required = true)
    private String paymentMethod;

    @XmlElement(name = "penznem", required = true)
    private String currency;

    @XmlElement(name = "devizabank")
    private String exchangeBank;

    @XmlElement(name = "devizaarf")
    private Double exchangeRate;

    @XmlElement(name = "megjegyzes")
    private String comment;

    @XmlElement(name = "fokonyvVevo")
    private String ledgerCustomer;

    @XmlElement(name = "teszt", required = true)
    private boolean test;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancelledReceiptNumber() {
        return cancelledReceiptNumber;
    }

    public void setCancelledReceiptNumber(String cancelledReceiptNumber) {
        this.cancelledReceiptNumber = cancelledReceiptNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getExchangeBank() {
        return exchangeBank;
    }

    public void setExchangeBank(String exchangeBank) {
        this.exchangeBank = exchangeBank;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLedgerCustomer() {
        return ledgerCustomer;
    }

    public void setLedgerCustomer(String ledgerCustomer) {
        this.ledgerCustomer = ledgerCustomer;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
}
