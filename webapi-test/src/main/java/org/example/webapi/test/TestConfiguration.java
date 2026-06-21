package org.example.webapi.test;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.reactive.server.WebTestClient;

@Configuration
public class TestConfiguration {

    private final String apiUrl;

    public TestConfiguration(
        @Value("${api.url}") String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Bean
    public WebTestClient webTestClient() {
        return WebTestClient.bindToServer()
                .baseUrl(apiUrl)
                .responseTimeout(Duration.ofMinutes(10))
                .build();
    }

    @Bean
    public WebResourceTypeIdResolver webResourceTypeIdResolver() {
        return new WebResourceTypeIdResolver();
    }
}
