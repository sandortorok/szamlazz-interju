# Nyugtakezelő Alkalmazás

Spring Boot + Angular alkalmazás nyugták létrehozására és kezelésére a Számlázz.hu API-n keresztül.

## Technológiák

- **Backend:** Spring Boot 4.0.1 + MySQL
- **Frontend:** Angular 21
- **Infrastruktúra:** Docker + Docker Compose

## Gyors Indítás

1. **Környezeti változók beállítása**
   ```bash
   # Másold le a .env.example-t és állítsd be az értékeket
   cp .env.example .env
   ```
   Szerkeszd a `.env` fájlt:
   - `SZAMLAZZ_API_KEY` - A Számlázz.hu API kulcsod
   - `MYSQL_DATABASE`, `MYSQL_USER`, `MYSQL_PASSWORD` - MySQL adatbázis beállítások

2. **Indítás**
   ```bash
   docker compose up -d
   ```

3. **Elérés**
   - Frontend: http://localhost:4200
   - Backend API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html

## API Dokumentáció

### Számlázz.hu Integráció Folyamata

```mermaid
sequenceDiagram
    participant F as Frontend
    participant B as Backend
    participant S as Számlázz.hu API
    participant DB as MySQL

    F->>B: POST /api/receipts (JSON)
    
    Note over B: ReceiptRequest → XML<br/>(JAXB marshalling)
    
    B->>S: POST multipart/form-data<br/>(action-xmlnyugtacreate.xml)
    
    S-->>B: XML válasz<br/>(xmlnyugtavalasz)
    
    Note over B: XML → ReceiptResponse<br/>(JAXB unmarshalling)
    
    alt Sikeres válasz
        B->>DB: Nyugta mentése
        B-->>F: 200 OK + ReceiptResponse
    else Hiba a Számlázz.hu-tól
        B-->>F: 502 Bad Gateway + ErrorResponse
    end
```

### Swagger UI

Az összes API végpont dokumentációja interaktívan elérhető a Swagger UI-ban.

![Swagger API Endpoints](./readme-images/swagger.png)

## Adatbázis Szerkezete

![Adatbázis Séma](./readme-images/db-structure.png)

## Alkalmazás Felület

### Nyugták Listázása

![Nyugták Listája](./readme-images/frontend-nyugta-lista.png)

### Új Nyugta Létrehozása

![Új Nyugta](./readme-images/frontend-uj-nyugta.png)

### Nyugta Részletek

![Nyugta Részletek](./readme-images/frontend-nyugta-reszletek.png)


