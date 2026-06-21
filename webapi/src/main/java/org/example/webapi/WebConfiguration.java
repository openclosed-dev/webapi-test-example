package org.example.webapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public WebResourceTypeIdResolver webResourceTypeIdResolver() {
        return new WebResourceTypeIdResolver();
    }
}
