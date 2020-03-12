package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.carbrands.CarBrandDto;
import com.kodilla.carsbackend.mapper.CarBrandMapper;
import com.kodilla.carsbackend.repository.CarBrandRepository;
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
public class CarBrandServiceTest {

    @InjectMocks
    private CarBrandService carBrandService;

    @Mock
    private CarBrandRepository carBrandRepository;

    @Mock
    private CarBrandMapper carBrandMapper;

    @Test
    public void testGetAllCarBrands() {
        //Given
        List<CarBrand> carBrands = new ArrayList<>();
        carBrands.add(new CarBrand("Test brand name", 1972));
        carBrands.add(new CarBrand("Test brand name 1", 1980));

        List<CarBrandDto> carBrandDtos = new ArrayList<>();
        carBrandDtos.add(new CarBrandDto(1L, "Test brand name", 1972));
        carBrandDtos.add(new CarBrandDto(2L, "Test brand 1", 1980));

        when(carBrandMapper.mapToCarBrandDtoList(carBrands)).thenReturn(carBrandDtos);
        when(carBrandRepository.findAll()).thenReturn(carBrands);

        //When
        List<CarBrandDto> fetcherCarBrands = carBrandService.getAllCarBrands();

        //Then
        assertEquals(2, fetcherCarBrands.size());
    }

    @Test
    public void testGetCarBrandById() throws Exception {
        //Given
        CarBrand carBrand = new CarBrand("Test brand name", 1972);
        Optional<CarBrand> optionalCarBrand = Optional.of(carBrand);
        CarBrandDto carBrandDto = new CarBrandDto(1L, "Test brand name", 1972);

        when(carBrandMapper.mapToCarBrandDto(carBrand)).thenReturn(carBrandDto);
        when(carBrandRepository.findById(1L)).thenReturn(optionalCarBrand);

        //When
        CarBrandDto fetchedCarBrand = carBrandService.getCarBrandById(1L);

        //Then
        assertEquals(1L, fetchedCarBrand.getId().longValue());
        assertEquals("Test brand name", fetchedCarBrand.getBrandName());
        assertEquals(1972, fetchedCarBrand.getConstructionYear());
    }
}