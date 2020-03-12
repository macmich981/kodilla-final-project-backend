package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.carbrands.CarBrandDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CarBrandMapperTest {

    @InjectMocks
    private CarBrandMapper carBrandMapper;

    @Test
    public void testMapToCarBrand() {
        //Given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "Test brand name", 1982);

        //When
        CarBrand carBrand = carBrandMapper.mapToCarBrand(carBrandDto);

        //Then
        assertNull(carBrand.getId());
        assertEquals("Test brand name", carBrand.getBrandName());
        assertEquals(1982, carBrand.getConstructionYear());
        assertEquals(0, carBrand.getCars().size());
    }

    @Test
    public void testMapToCarBrandDto() {
        //Given
        CarBrand carBrand = new CarBrand(1L, "Test brand name", 1982, new ArrayList<>());

        //When
        CarBrandDto carBrandDto = carBrandMapper.mapToCarBrandDto(carBrand);

        //Then
        assertEquals(1L, carBrandDto.getId().longValue());
        assertEquals("Test brand name", carBrandDto.getBrandName());
        assertEquals(1982, carBrandDto.getConstructionYear());

    }

    @Test
    public void testMapToCarBrandDtoList() {
        //Given
        List<CarBrand> carBrands = new ArrayList<>();
        carBrands.add(new CarBrand(1L, "Test brand name", 1982, new ArrayList<>()));
        carBrands.add(new CarBrand(2L, "Test brand name 1", 2000, new ArrayList<>()));

        //When
        List<CarBrandDto> carBrandDtos = carBrandMapper.mapToCarBrandDtoList(carBrands);

        //Then
        assertEquals(2, carBrandDtos.size());
    }
}