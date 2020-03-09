package com.kodilla.carsbackend.controller;

import com.google.gson.Gson;
import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.cars.Car;
import com.kodilla.carsbackend.domain.cars.CarDto;
import com.kodilla.carsbackend.domain.cars.State;
import com.kodilla.carsbackend.domain.rents.Rent;
import com.kodilla.carsbackend.mapper.CarMapper;
import com.kodilla.carsbackend.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private CarMapper carMapper;

    @Test
    public void shouldReturnCars() throws Exception {
        //Given
        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(new CarDto(1L, 1L, "DZA 123456", 2000, State.AVAILABLE.name()));
        carDtos.add(new CarDto(2L, 1L, "DDZ 987654", 1995, State.RENTED.name()));

        when(carService.getAllCars()).thenReturn(carDtos);

        //When & Then
        mockMvc.perform(get("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].carBrandId", is(1)))
                .andExpect(jsonPath("$[0].registrationNumber", is("DZA 123456")))
                .andExpect(jsonPath("$[0].productionYear", is(2000)))
                .andExpect(jsonPath("$[0].state", is("AVAILABLE")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].carBrandId", is(1)))
                .andExpect(jsonPath("$[1].registrationNumber", is("DDZ 987654")))
                .andExpect(jsonPath("$[1].productionYear", is(1995)))
                .andExpect(jsonPath("$[1].state", is("RENTED")));
    }

    @Test
    public void shouldReturnCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L, 1L, "DZA 123456", 2000, State.AVAILABLE.name());

        when(carService.getCarById(carDto.getId())).thenReturn(carDto);

        //When & Then
        mockMvc.perform(get("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.carBrandId", is(1)))
                .andExpect(jsonPath("$.registrationNumber", is("DZA 123456")))
                .andExpect(jsonPath("$.productionYear", is(2000)))
                .andExpect(jsonPath("$.state", is("AVAILABLE")));
    }

    @Test
    public void shouldUpdateCarStateToDamaged() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L, 1L, "DZA 123456", 2000, State.AVAILABLE.name());
        CarDto updatedCarDto = new CarDto(1L, 1L, "DZA 123456", 2000, State.DAMAGED.name());

        when(carService.updateCarStateToDamaged(carDto.getId())).thenReturn(updatedCarDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc.perform(put("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is("DAMAGED")));
    }

    @Test
    public void shouldCreateCar() throws Exception {
        //Given
        Car car = new Car(1L, "DZA 123456", 2000, State.AVAILABLE.name(), new Rent(), new CarBrand());
        CarDto carDto = new CarDto(1L, 1L, "DZA 123456", 2000, State.AVAILABLE.name());

        when(carMapper.mapToCar(carDto)).thenReturn(car);
        when(carService.saveCar(carDto)).thenReturn(car);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCar() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}