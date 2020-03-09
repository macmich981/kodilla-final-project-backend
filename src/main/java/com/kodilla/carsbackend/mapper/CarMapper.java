package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.cars.Car;
import com.kodilla.carsbackend.domain.cars.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getRegistrationNumber(),
                carDto.getProductionYear());
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getCarBrand().getId(),
                car.getRegistrationNumber(),
                car.getProductionYear(),
                car.getState());
    }

    public List<CarDto> mapToCopyDtoList(final List<Car> carList) {
        return carList.stream()
                .map(cb ->
                        new CarDto(
                                cb.getId(),
                                cb.getCarBrand().getId(),
                                cb.getRegistrationNumber(),
                                cb.getProductionYear(),
                                cb.getState()))
                .collect(Collectors.toList());
    }
}
