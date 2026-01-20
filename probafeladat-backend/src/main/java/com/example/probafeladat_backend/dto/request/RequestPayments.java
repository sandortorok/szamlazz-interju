package com.example.probafeladat_backend.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class RequestPayments {

    @XmlElement(name = "kifizetes", required = true)
    private List<RequestPayment> payments;

    public List<RequestPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<RequestPayment> payments) {
        this.payments = payments;
    }
}
