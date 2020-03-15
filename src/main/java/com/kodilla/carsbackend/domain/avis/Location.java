package com.kodilla.carsbackend.domain.avis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    @JsonProperty("brand")
    private String brand;

    @JsonProperty("name")
    private String name;

    @JsonProperty("airport_location")
    private boolean airportLocation;

    @JsonProperty("address")
    private Address address;
}
