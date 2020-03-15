package com.kodilla.carsbackend.domain.avis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvisQueryDto {
    private final String brand;
    private final String countryCode;
    private final String keyword;
    private final String transactionId;
}
