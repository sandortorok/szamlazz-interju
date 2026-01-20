package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponsePayments {

    @XmlElement(name = "kifizetes", required = true)
    private List<ResponsePayment> payments;

    public List<ResponsePayment> getPayments() {
        return payments;
    }

    public void setPayments(List<ResponsePayment> payments) {
        this.payments = payments;
    }
}
