package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseItem {

    @XmlElement(name = "megnevezes", required = true)
    private String name;

    @XmlElement(name = "azonosito")
    private String itemId;

    @XmlElement(name = "nettoEgysegar", required = true)
    private Double netUnitPrice;

    @XmlElement(name = "mennyiseg", required = true)
    private Double quantity;

    @XmlElement(name = "mennyisegiEgyseg", required = true)
    private String unit;

    @XmlElement(name = "netto", required = true)
    private Double netTotal;

    @XmlElement(name = "afatipus")
    private String vatType;

    @XmlElement(name = "afakulcs", required = true)
    private Double vatRate;

    @XmlElement(name = "afa", required = true)
    private Double vatAmount;

    @XmlElement(name = "brutto", required = true)
    private Double grossTotal;

    @XmlElement(name = "fokonyv")
    private ResponseItemLedger ledger;

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

    public Double getNetUnitPrice() {
        return netUnitPrice;
    }

    public void setNetUnitPrice(Double netUnitPrice) {
        this.netUnitPrice = netUnitPrice;
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

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
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

    public ResponseItemLedger getLedger() {
        return ledger;
    }

    public void setLedger(ResponseItemLedger ledger) {
        this.ledger = ledger;
    }
}
