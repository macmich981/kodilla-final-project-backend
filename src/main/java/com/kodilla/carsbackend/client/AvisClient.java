package com.kodilla.carsbackend.client;

import com.kodilla.carsbackend.config.AvisConfig;
import com.kodilla.carsbackend.domain.avis.AvisQueryDto;
import com.kodilla.carsbackend.domain.avis.AvisResponse;
import com.kodilla.carsbackend.domain.avis.Location;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
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

        try {
            ResponseEntity<AvisResponse> response = restTemplate.exchange(createUrl(avisQueryDto), HttpMethod.GET, entity, AvisResponse.class);
            return response.getBody().getLocations();
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    private URI createUrl(AvisQueryDto avisQueryDto) {
        return UriComponentsBuilder.fromHttpUrl(avisConfig.getAvisApiEndpoint())
                .queryParam("brand", avisQueryDto.getBrand())
                .queryParam("country_code", avisQueryDto.getCountryCode())
                .queryParam("keyword", avisQueryDto.getKeyword())
                .queryParam("transaction_id", avisQueryDto.getTransactionId())
                .build().encode().toUri();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("client_id", avisConfig.getClientId());
        headers.set("Authorization", createAuthorization());

        return headers;
    }

    public String createAuthorization() {
        try {
            OAuthClient client = new OAuthClient(new URLConnectionClient());

            OAuthClientRequest request = OAuthClientRequest.tokenLocation("https://stage.abgapiservices.com/oauth/token/v1")
                    .setGrantType(GrantType.CLIENT_CREDENTIALS)
                    .setClientId(avisConfig.getClientId())
                    .setClientSecret(avisConfig.getClientSecret())
                    .buildBodyMessage();

            return "Bearer " + client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class).getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
