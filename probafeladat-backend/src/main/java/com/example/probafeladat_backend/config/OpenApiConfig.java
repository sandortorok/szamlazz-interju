package com.example.probafeladat_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI receiptOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("Receipt API Support");

        Info info = new Info()
                .title("Nyugtakezelő API")
                .version("1.0.0")
                .description("REST API nyugták létrehozásához és kezeléséhez a Számlázz.hu integrációval")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
