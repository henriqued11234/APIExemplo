package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://abcd-ndjr.onrender.com",
                        "https://outra-pagina.com",
                        "http://localhost:5500",
                        "http://127.0.0.1:5500",
                        "null" // Libera abrir o arquivo HTML direto do computador (file:///)
                )
                .allowCredentials(true)
                .allowedHeaders(
                        "origin", "content-type", "accept", "authorization", "x-requested-with"
                )
                .allowedMethods(
                        "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"
                )
                .maxAge(86400);
    }
}