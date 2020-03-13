package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.cars.Car;
import com.kodilla.carsbackend.domain.cars.CarDto;
import com.kodilla.carsbackend.domain.cars.State;
import com.kodilla.carsbackend.mapper.CarMapper;
import com.kodilla.carsbackend.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @Test
    public void testGetAllCars() {
        //Given
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("DZA 111111", 2000));
        cars.add(new Car("DZA 222222", 2010));

        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(new CarDto(1L, 1L, "DZA 111111", 2000, State.AVAILABLE.name()));
        carDtos.add(new CarDto(2L, 1L, "DZA 222222", 2010, State.AVAILABLE.name()));

        when(carMapper.mapToCarDtoList(cars)).thenReturn(carDtos);
        when(carRepository.findAll()).thenReturn(cars);

        //When
        List<CarDto> fetchedCars = carService.getAllCars();

        //Then
        assertEquals(2, fetchedCars.size());
    }

    @Test
    public void testGetCarById() throws Exception {
        //Given
        Car car = new Car("DZA 111111", 2000);
        Optional<Car> optionalCar = Optional.of(car);
        CarDto carDto = new CarDto(1L, 1L,"DZA 111111", 2000, State.AVAILABLE.name());

        when(carMapper.mapToCarDto(car)).thenReturn(carDto);
        when(carRepository.findById(1L)).thenReturn(optionalCar);

        //When
        CarDto fetchedCar = carService.getCarById(1L);

        //Then
        assertEquals(1L, fetchedCar.getId().longValue());
        assertEquals(1L, fetchedCar.getCarBrandId().longValue());
        assertEquals("DZA 111111", fetchedCar.getRegistrationNumber());
        assertEquals(2000, fetchedCar.getProductionYear());
        assertEquals("AVAILABLE", fetchedCar.getState());
    }
}