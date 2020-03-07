package com.kodilla.carsbackend.domain.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private Long carBrandId;
    private String registrationNumber;
    private int productionYear;
    private String state;
}
