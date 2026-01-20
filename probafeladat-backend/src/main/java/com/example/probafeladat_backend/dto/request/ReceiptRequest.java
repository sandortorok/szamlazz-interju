package com.example.probafeladat_backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmlnyugtacreate")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Nyugta létrehozási kérés")
public class ReceiptRequest {

    @XmlElement(name = "beallitasok", required = true)
    @NotNull(message = "Beállítások kötelező")
    @Valid
    @Schema(description = "Beállítások (API kulcs, PDF letöltés)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Settings settings;

    @XmlElement(name = "fejlec", required = true)
    @NotNull(message = "Fejléc kötelező")
    @Valid
    @Schema(description = "Nyugta fejléc adatok", requiredMode = Schema.RequiredMode.REQUIRED)
    private Header header;

    @XmlElement(name = "tetelek", required = true)
    @NotNull(message = "Tételek kötelezőek")
    @Valid
    @Schema(description = "Nyugta tételek", requiredMode = Schema.RequiredMode.REQUIRED)
    private RequestItems items;

    @XmlElement(name = "kifizetesek")
    @Valid
    @Schema(description = "Kifizetések (opcionális)")
    private RequestPayments payments;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public RequestItems getItems() {
        return items;
    }

    public void setItems(RequestItems items) {
        this.items = items;
    }

    public RequestPayments getPayments() {
        return payments;
    }

    public void setPayments(RequestPayments payments) {
        this.payments = payments;
    }
}
