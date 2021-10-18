package com.knor.taxiaggregator.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class YandexClientConfiguration {

    @Value("${yandex.url}")
    private String url;

    @Bean
    public WebClient configure() {
        return WebClient.create(url);
    }
}
