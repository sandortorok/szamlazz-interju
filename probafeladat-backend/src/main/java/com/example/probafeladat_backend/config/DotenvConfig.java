package com.example.probafeladat_backend.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@Configuration
public class DotenvConfig {

    @PostConstruct
    public void loadEnv() {
        // Docker környezetben a környezeti változókat a Docker Compose adja át
        // Csak helyi fejlesztéskor használjuk a .env fájlt
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        dotenv.entries().forEach(entry -> {
            // Csak akkor írjuk felül, ha még nincs beállítva a környezeti változó
            if (System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }
}