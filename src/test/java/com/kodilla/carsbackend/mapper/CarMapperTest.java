package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.cars.Car;
import com.kodilla.carsbackend.domain.cars.CarDto;
import com.kodilla.carsbackend.domain.cars.State;
import com.kodilla.carsbackend.domain.rents.Rent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CarMapperTest {

    @InjectMocks
    private CarMapper carMapper;

    @Test
    public void testMapToCar() {
        //Given
        CarDto carDto = new CarDto(1L, 1L, "DZA 111111", 2000, State.AVAILABLE.name());

        //When
        Car car = carMapper.mapToCar(carDto);

        //Then
        assertNull(car.getId());
        assertEquals("DZA 111111", car.getRegistrationNumber());
        assertEquals(2000, car.getProductionYear());
        assertEquals("AVAILABLE", car.getState());
        assertNull(car.getCarBrand());
        assertNull(car.getRent());
    }

    @Test
    public void testMapToCarDto() {
        //Given
        CarBrand carBrand = new CarBrand(1L, "Test brand name", 1982, new ArrayList<>());
        Car car = new Car(1L, "DZA 111111", 2000, State.AVAILABLE.name(), new Rent(), carBrand);

        //When
        CarDto carDto = carMapper.mapToCarDto(car);

        //Then
        assertEquals(1L, carDto.getId().longValue());
        assertEquals("DZA 111111", carDto.getRegistrationNumber());
        assertEquals(2000, carDto.getProductionYear());
        assertEquals("AVAILABLE", carDto.getState());
        assertEquals(1L, carDto.getCarBrandId().longValue());
    }

    @Test
    public void testMapToCarDtoList() {
        //Given
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1L, "DZA 111111", 2000, State.AVAILABLE.name(), new Rent(), new CarBrand()));
        cars.add(new Car(2L, "DZA 222222", 2010, State.AVAILABLE.name(), new Rent(), new CarBrand()));

        //When
        List<CarDto> carDtos = carMapper.mapToCarDtoList(cars);

        //Then
        assertEquals(2, carDtos.size());
    }
}