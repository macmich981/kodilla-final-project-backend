package com.kodilla.carsbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AvisConfig {
    @Value("${avis.api.endpoint}")
    private String avisApiEndpoint;

    @Value("${avis.api.client_id}")
    private String clientId;

    @Value("${avis.api.client_secret}")
    private String clientSecret;
}
