package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.cars.Car;
import com.kodilla.carsbackend.domain.cars.State;
import com.kodilla.carsbackend.domain.rents.Rent;
import com.kodilla.carsbackend.domain.rents.RentDto;
import com.kodilla.carsbackend.domain.users.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RentMapperTest {

    @InjectMocks
    private RentMapper rentMapper;

    @Test
    public void testMapToRent() {
        //Given

        Date rentDate = new Date();
        Date returnDate = new Date();
        RentDto rentDto = new RentDto(1L, 1L, 1L, rentDate, returnDate);

        //When
        Rent rent = rentMapper.mapToRent(rentDto);

        //Then
        assertNull(rent.getId());
        assertNull(rent.getUser());
        assertNull(rent.getCar());
        assertEquals(rentDate, rent.getRentDate());
        assertEquals(returnDate, rent.getReturnDate());
    }

    @Test
    public void testMapToRentDto() {
        //Given
        Date rentDate = new Date();
        Date returnDate = new Date();
        Car car = new Car(1L, "DZA 111111", 2000, State.AVAILABLE.name(), new Rent(), new CarBrand());
        User user = new User(1L, "Test name", "Test lastname", "AAA 000000", "QWERTY", new ArrayList<>());
        Rent rent = new Rent(1L, user, car, rentDate, returnDate);

        //When
        RentDto rentDto = rentMapper.mapToRentDto(rent);

        //Then
        assertEquals(1L, rentDto.getId().longValue());
        assertEquals(1L, rentDto.getUserId().longValue());
        assertEquals(1L, rentDto.getCarId().longValue());
        assertEquals(rentDate, rentDto.getRentDate());
        assertEquals(returnDate, rentDto.getReturnDate());
    }

    @Test
    public void testMapToRentDtoList() {
        //Given
        List<Rent> rents = new ArrayList<>();
        rents.add(new Rent(1L, new User(), new Car(), new Date(), new Date()));
        rents.add(new Rent(2L, new User(), new Car(), new Date(), new Date()));

        //When
        List<RentDto> rentDtos = rentMapper.mapToRentDtoList(rents);

        //Then
        assertEquals(2, rentDtos.size());
    }
}