package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Totals {

    @XmlElement(name = "afakulcsossz", required = true)
    private List<VatRateSummary> vatRateSummaries;

    @XmlElement(name = "totalossz", required = true)
    private TotalSum totalSum;

    public List<VatRateSummary> getVatRateSummaries() {
        return vatRateSummaries;
    }

    public void setVatRateSummaries(List<VatRateSummary> vatRateSummaries) {
        this.vatRateSummaries = vatRateSummaries;
    }

    public TotalSum getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(TotalSum totalSum) {
        this.totalSum = totalSum;
    }
}
