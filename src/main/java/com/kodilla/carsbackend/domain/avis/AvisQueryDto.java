package com.kodilla.carsbackend.domain.avis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvisQueryDto {
    private String brand;
    private String countryCode;
    private String keyword;
    private String transactionId;
}
