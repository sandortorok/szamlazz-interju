package com.example.probafeladat_backend.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseItems {

    @XmlElement(name = "tetel", required = true)
    private List<ResponseItem> items;

    public List<ResponseItem> getItems() {
        return items;
    }

    public void setItems(List<ResponseItem> items) {
        this.items = items;
    }
}
