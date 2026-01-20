package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Receipt {

    @XmlElement(name = "alap", required = true)
    private Base base;

    @XmlElement(name = "tetelek", required = true)
    private ResponseItems items;

    @XmlElement(name = "kifizetesek")
    private ResponsePayments payments;

    @XmlElement(name = "osszegek", required = true)
    private Totals totals;

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public ResponseItems getItems() {
        return items;
    }

    public void setItems(ResponseItems items) {
        this.items = items;
    }

    public ResponsePayments getPayments() {
        return payments;
    }

    public void setPayments(ResponsePayments payments) {
        this.payments = payments;
    }

    public Totals getTotals() {
        return totals;
    }

    public void setTotals(Totals totals) {
        this.totals = totals;
    }
}
