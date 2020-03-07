package com.kodilla.carsbackend.domain.carbrands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandDto {
    private Long id;
    private String brandName;
    private int constructionYear;
}
