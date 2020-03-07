package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.carbrands.CarBrandNotFoundException;
import com.kodilla.carsbackend.domain.cars.*;
import com.kodilla.carsbackend.mapper.CarMapper;
import com.kodilla.carsbackend.repository.CarBrandRepository;
import com.kodilla.carsbackend.repository.CarRepository;
import com.kodilla.carsbackend.repository.RentRepository;
import com.kodilla.carsbackend.validator.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private CarValidator carValidator;

    public static final String ERROR_MSG = "Car not found";

    public Car saveCar(final CarDto carDto) throws CarBrandNotFoundException, CarRegistrationNumberException {
        CarBrand carBrand = carBrandRepository.findById(carDto.getCarBrandId()).orElseThrow(() -> new CarBrandNotFoundException(CarBrandService.ERROR_MSG));
        if (!carValidator.validateRegistrationNumber(carDto.getRegistrationNumber())) {
            throw new CarRegistrationNumberException("Given registration number already exist");
        }
        Car car = carMapper.mapToCopy(carDto);
        car.setCarBrand(carBrand);
        carBrand.getCarCopies().add(car);
        return carRepository.save(car);
    }

    public List<CarDto> getAllCars() {
        return carMapper.mapToCopyDtoList(carRepository.findAll());
    }

    public CarDto getCarById(final Long id) throws CarNotFoundException {
        return carMapper.mapToCopyDto(carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(ERROR_MSG)));
    }

    public CarDto updateCarStateToDamaged(final Long id) throws CarNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(ERROR_MSG));
        car.setState(State.DAMAGED.name());
        return carMapper.mapToCopyDto(carRepository.save(car));
    }

    public void deleteCarById(final Long id) throws CarNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(ERROR_MSG));
        carRepository.delete(car);
    }
}
