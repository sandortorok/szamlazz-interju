# Nyugtakezelő Alkalmazás

Spring Boot + Angular alkalmazás nyugták létrehozására és kezelésére a Számlázz.hu API-n keresztül.

## Technológiák

**Backend:**
- Spring Boot 4.0.1
- MySQL

**Frontend:**
- Angular 21

## Quick Start with Docker

### Előfeltételek
- Docker
- Docker Compose

### Indítás

1. **Repository klónozása**
```bash
git clone https://github.com/sandortorok/szamlazz-interju.git
cd szamlazz-interju
```

2. **.env fájl létrehozása**
```bash
cp .env.example .env
```

3. **.env fájl szerkesztése** - állítsd be a Számlázz.hu API kulcsot:
```env
SZAMLAZZ_API_KEY=ide_jon_a_valodi_api_kulcs
```

4. **Alkalmazás indítása**
```bash
docker-compose up -d
```

5. **Elérés**
- Frontend: http://localhost:4200
- Backend API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

### Docker parancsok

```bash
# Összes service indítása
docker-compose up -d

# Logok megtekintése
docker-compose logs -f

# Service-ek leállítása
docker-compose down

# Újraépítés és újraindítás
docker-compose up -d --build

# Leállítás és adatbázis törlése (tiszta újrakezdés)
docker-compose down -v
```

## Manuális telepítés (Docker nélkül)

### Backend

2. **Környezeti változók beállítása**
   ```bash
   cd probafeladat-backend
   cp .env.example .env
   # Szerkeszd a .env fájlt:
   # - DB_HOST, DB_PORT, DB_NAME (adatbázis kapcsolat)
   # - DB_USERNAME, DB_PASSWORD (adatbázis authentikáció)
   # - SZAMLAZZ_API_KEY (Számlázz.hu API kulcs)
   ```

3. **Backend indítása**
   ```bash
   ./mvnw spring-boot:run
   # vagy Windows-on:
   mvnw.cmd spring-boot:run
   ```
   
   Backend elérhető: `http://localhost:8080`
   **Swagger UI:** `http://localhost:8080/swagger-ui.html`
   **API Docs:** `http://localhost:8080/api-docs`

### Frontend

1. **Környezeti változók beállítása**
   ```bash
   cd probafeladat-frontend
   cp src/environments/environment.example.ts src/environments/environment.ts
   # Szerkeszd az environment.ts fájlt a saját API kulcsoddal
   ```

2. **Függőségek telepítése**
   ```bash
   npm install
   ```

3. **Frontend indítása**
   ```bash
   npm start
   # vagy
   ng serve
   ```
   
   Frontend elérhető: `http://localhost:4200`

## API Végpontok

- `POST /api/receipts` - Új nyugta létrehozása
- `GET /api/receipts` - Összes nyugta listázása (összefoglaló)
- `GET /api/receipts/{id}` - Nyugta részletes adatai
- `GET /api/receipts/test` - Teszt nyugta minta XML-ből
