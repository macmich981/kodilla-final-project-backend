package com.kodilla.carsbackend.controller;

import com.google.gson.Gson;
import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.carbrands.CarBrandDto;
import com.kodilla.carsbackend.mapper.CarBrandMapper;
import com.kodilla.carsbackend.service.CarBrandService;
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
@WebMvcTest(CarBrandController.class)
public class CarBrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarBrandService carBrandService;

    @MockBean
    private CarBrandMapper carBrandMapper;

    @Test
    public void shouldReturnCarBrands() throws Exception {
        //Given
        List<CarBrandDto> carBrandDtos = new ArrayList<>();
        carBrandDtos.add(new CarBrandDto(1L, "Test brand", 1968));
        carBrandDtos.add(new CarBrandDto(2L, "Test brand 2", 1978));

        when(carBrandService.getAllCarBrands()).thenReturn(carBrandDtos);

        //When & Then
        mockMvc.perform(get("/v1/carbrands")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brandName", is("Test brand")))
                .andExpect(jsonPath("$[0].constructionYear", is(1968)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].brandName", is("Test brand 2")))
                .andExpect(jsonPath("$[1].constructionYear", is(1978)));

    }

    @Test
    public void shouldReturnCarBrand() throws Exception {
        //Given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "Test brand", 2000);

        when(carBrandService.getCarBrandById(carBrandDto.getId())).thenReturn(carBrandDto);

        //When & Then
        mockMvc.perform(get("/v1/carbrands/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.brandName", is("Test brand")))
                .andExpect(jsonPath("$.constructionYear", is(2000)));
    }

    @Test
    public void shouldCreateCarBrand() throws Exception {
        //Given
        CarBrand carBrand = new CarBrand("Test brand", 2000);
        CarBrandDto carBrandDto = new CarBrandDto(1L, "Test brand", 2000);

        when(carBrandMapper.mapToCarBrand(carBrandDto)).thenReturn(carBrand);
        when(carBrandService.saveCarBrand(carBrandDto)).thenReturn(carBrand);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carBrandDto);

        //When & Then
        mockMvc.perform(post("/v1/carbrands")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCarBrand() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/carbrands/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}