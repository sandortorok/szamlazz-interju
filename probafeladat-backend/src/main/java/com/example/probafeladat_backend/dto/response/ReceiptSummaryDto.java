package com.example.probafeladat_backend.dto.response;

public class ReceiptSummaryDto {
    
    private Long id;
    private String receiptNumber;
    private String date;
    private Double totalNet;
    private Double totalGross;

    public ReceiptSummaryDto() {
    }

    public ReceiptSummaryDto(Long id, String receiptNumber, String date, Double totalNet, Double totalGross) {
        this.id = id;
        this.receiptNumber = receiptNumber;
        this.date = date;
        this.totalNet = totalNet;
        this.totalGross = totalGross;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(Double totalNet) {
        this.totalNet = totalNet;
    }

    public Double getTotalGross() {
        return totalGross;
    }

    public void setTotalGross(Double totalGross) {
        this.totalGross = totalGross;
    }
}
