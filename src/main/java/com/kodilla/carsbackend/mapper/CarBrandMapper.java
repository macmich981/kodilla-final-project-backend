package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.carbrands.CarBrandDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarBrandMapper {
    public CarBrand mapToCarBrand(final CarBrandDto carBrandDto) {
        return new CarBrand(carBrandDto.getBrandName(), carBrandDto.getConstructionYear());
    }

    public CarBrandDto mapToCarBrandDto(final CarBrand carBrand) {
        return new CarBrandDto(carBrand.getId(), carBrand.getBrandName(), carBrand.getConstructionYear());
    }

    public List<CarBrandDto> mapToCarBrandDtoList(final List<CarBrand> carBrandList) {
        return carBrandList.stream()
                .map(cb -> new CarBrandDto(cb.getId(), cb.getBrandName(), cb.getConstructionYear()))
                .collect(Collectors.toList());
    }
}
