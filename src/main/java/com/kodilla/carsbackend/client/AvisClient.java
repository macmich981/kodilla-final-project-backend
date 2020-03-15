package com.kodilla.carsbackend.client;

import com.kodilla.carsbackend.config.AvisConfig;
import com.kodilla.carsbackend.domain.avis.AvisQueryDto;
import com.kodilla.carsbackend.domain.avis.AvisResponse;
import com.kodilla.carsbackend.domain.avis.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
public class AvisClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AvisConfig avisConfig;

    public List<Location> getLocations(AvisQueryDto avisQueryDto) {
        HttpHeaders headers = createHeaders();
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<AvisResponse> response = restTemplate.exchange(createUrl(avisQueryDto), HttpMethod.GET, entity, AvisResponse.class);
        return response.getBody().getLocations();
    }

    private URI createUrl(AvisQueryDto avisQueryDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(avisConfig.getAvisApiEndpoint())
                .queryParam("brand", avisQueryDto.getBrand())
                .queryParam("country_code", avisQueryDto.getCountryCode())
                .queryParam("keyword", avisQueryDto.getKeyword())
                .queryParam("transaction_id", avisQueryDto.getTransactionId())
                .build().encode().toUri();

        return url;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("client_id", avisConfig.getClientId());
        headers.set("Authorization", avisConfig.getAuthorization());

        return headers;
    }
}
