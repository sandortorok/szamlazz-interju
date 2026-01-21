package com.example.probafeladat_backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipts")
public class ReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean success;
    private Integer errorCode;
    private String errorMessage;
    
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String receiptPdf;  // nyugtaPdf - Base64 encoded PDF

    private Integer receiptId;
    private String callId;
    private String receiptNumber;
    private String type;
    private boolean cancelled;
    private String cancelledReceiptNumber;
    private String date;
    private String paymentMethod;
    private String currency;
    private String exchangeBank;
    private Double exchangeRate;
    private String comment;
    private String ledgerCustomer;
    private boolean test;

    private Double totalNet;
    private Double totalVat;
    private Double totalGross;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("receipt-items")
    private List<ReceiptItemEntity> items = new ArrayList<>();

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("receipt-payments")
    private List<PaymentEntity> payments = new ArrayList<>();
    
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("receipt-vat-summaries")
    private List<VatRateSummaryEntity> vatRateSummaries = new ArrayList<>();

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
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

    public String getLedgerCustomer() {
        return ledgerCustomer;
    }

    public void setLedgerCustomer(String ledgerCustomer) {
        this.ledgerCustomer = ledgerCustomer;
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

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public Double getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(Double totalNet) {
        this.totalNet = totalNet;
    }

    public Double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(Double totalVat) {
        this.totalVat = totalVat;
    }

    public Double getTotalGross() {
        return totalGross;
    }

    public void setTotalGross(Double totalGross) {
        this.totalGross = totalGross;
    }

    public List<ReceiptItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItemEntity> items) {
        this.items = items;
    }

    public List<VatRateSummaryEntity> getVatRateSummaries() {
        return vatRateSummaries;
    }

    public void setVatRateSummaries(List<VatRateSummaryEntity> vatRateSummaries) {
        this.vatRateSummaries = vatRateSummaries;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }

    public void addItem(ReceiptItemEntity item) {
        items.add(item);
        item.setReceipt(this);
    }

    public void addPayment(PaymentEntity payment) {
        payments.add(payment);
        payment.setReceipt(this);
    }

    public void addVatRateSummary(VatRateSummaryEntity vatRateSummary) {
        vatRateSummaries.add(vatRateSummary);
        vatRateSummary.setReceipt(this);
    }
}
