package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.rents.Rent;
import com.kodilla.carsbackend.domain.rents.RentDto;
import com.kodilla.carsbackend.mapper.RentMapper;
import com.kodilla.carsbackend.repository.RentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {

    @InjectMocks
    private RentService rentService;

    @Mock
    private RentMapper rentMapper;

    @Mock
    private RentRepository rentRepository;

    @Test
    public void testGetAllRents() {
        //Given
        List<Rent> rents = new ArrayList<>();
        rents.add(new Rent(new Date(), new Date()));
        rents.add(new Rent(new Date(), new Date()));

        List<RentDto> rentDtos = new ArrayList<>();
        rentDtos.add(new RentDto(1L, 1L, 1L, new Date(), new Date()));
        rentDtos.add(new RentDto(2L, 2L, 2L, new Date(), new Date()));

        when(rentMapper.mapToRentDtoList(rents)).thenReturn(rentDtos);
        when(rentRepository.findAll()).thenReturn(rents);

        //When
        List<RentDto> fetchedRents = rentService.getAllRents();

        //Then
        assertEquals(2, fetchedRents.size());
    }
}