package com.kodilla.carsbackend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class AvisClient {

    @Autowired
    private RestTemplate restTemplate;

    public String getLocations(final String brand, final String countryCode, final String keyword, final String transactionId) {
        URI url = UriComponentsBuilder.fromHttpUrl("https://stage.abgapiservices.com/cars/locations/v1")
                .queryParam("brand", brand)
                .queryParam("country_code", countryCode)
                .queryParam("keyword", keyword)
                .queryParam("transaction_id", transactionId)
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("client_id", "a8c647e5498a40c6b5da6c3bf793879e");
        headers.set("Authorization", "Bearer 0001L97neunqqW0wQoLAohJEdJLh");

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
