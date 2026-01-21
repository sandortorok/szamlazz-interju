package com.example.probafeladat_backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "API beállítások")
public class Settings {

    @XmlElement(name = "felhasznalo")
    @Schema(description = "Felhasználónév (opcionális)")
    private String username;

    @XmlElement(name = "jelszo")
    @Schema(description = "Jelszó (opcionális)")
    private String password;

    @XmlElement(name = "szamlaagentkulcs")
    @Schema(description = "Számlázz.hu API kulcs (username/password vagy ez szükséges)", 
            example = "your_api_key_here")
    private String apiKey;

    @XmlElement(name = "pdfLetoltes", required = true)
    @NotNull(message = "PDF letöltés beállítás kötelező")
    @Schema(description = "PDF letöltése (kötelező)", requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private boolean pdfDownload;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean isPdfDownload() {
        return pdfDownload;
    }

    public void setPdfDownload(boolean pdfDownload) {
        this.pdfDownload = pdfDownload;
    }
}
