package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.carbrands.CarBrandDto;
import com.kodilla.carsbackend.domain.carbrands.CarBrandNotFoundException;
import com.kodilla.carsbackend.mapper.CarBrandMapper;
import com.kodilla.carsbackend.repository.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandService {
    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarBrandMapper carBrandMapper;

    public static final String ERROR_MSG = "Car brand not found";

    public CarBrand saveCarBrand(final CarBrandDto carBrandDto) {
        return carBrandRepository.save(carBrandMapper.mapToCarBrand(carBrandDto));
    }

    public List<CarBrandDto> getAllCarBrands() {
        return carBrandMapper.mapToCarBrandDtoList(carBrandRepository.findAll());
    }

    public CarBrandDto getCarBrandById(final Long id) throws CarBrandNotFoundException {
        return carBrandMapper.mapToCarBrandDto(carBrandRepository.findById(id).orElseThrow(() -> new CarBrandNotFoundException(ERROR_MSG)));
    }

    public void deleteCarBrandById(final Long id) throws CarBrandNotFoundException {
        CarBrand carBrand = carBrandRepository.findById(id).orElseThrow(() -> new CarBrandNotFoundException(ERROR_MSG));
        carBrandRepository.delete(carBrand);
    }
}
