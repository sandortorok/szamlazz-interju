package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TotalSum {

    @XmlElement(name = "netto", required = true)
    private Double netTotal;

    @XmlElement(name = "afa", required = true)
    private Double vatTotal;

    @XmlElement(name = "brutto", required = true)
    private Double grossTotal;

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    public Double getVatTotal() {
        return vatTotal;
    }

    public void setVatTotal(Double vatTotal) {
        this.vatTotal = vatTotal;
    }

    public Double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(Double grossTotal) {
        this.grossTotal = grossTotal;
    }
}
