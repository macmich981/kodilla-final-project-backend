package com.kodilla.carsbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.carsbackend.domain.rents.Rent;
import com.kodilla.carsbackend.domain.rents.RentDto;
import com.kodilla.carsbackend.mapper.RentMapper;
import com.kodilla.carsbackend.service.RentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentService rentService;

    @MockBean
    private RentMapper rentMapper;

    @Test
    public void shouldReturnRents() throws Exception {
        //Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date firstRentDate = sdf.parse("2020-03-10");
        Date firstReturnDate = sdf.parse("2020-03-20");
        Date secondRentDate = sdf.parse("2020-04-15");
        Date secondReturnDate = sdf.parse("2020-04-27");

        List<RentDto> rentDtos = new ArrayList<>();
        rentDtos.add(new RentDto(1L, 1L, 1L, firstRentDate, firstReturnDate));
        rentDtos.add(new RentDto(2L, 2L, 2L, secondRentDate, secondReturnDate));

        when(rentService.getAllRents()).thenReturn(rentDtos);

        //When & Then
        mockMvc.perform(get("/v1/rents")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].carId", is(1)))
                .andExpect(jsonPath("$[0].rentDate", is("2020-03-10T07:00:00.000+0000")))
                .andExpect(jsonPath("$[0].returnDate", is("2020-03-20T07:00:00.000+0000")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].carId", is(2)))
                .andExpect(jsonPath("$[1].rentDate", is("2020-04-15T07:00:00.000+0000")))
                .andExpect(jsonPath("$[1].returnDate", is("2020-04-27T07:00:00.000+0000")));
    }

    @Test
    public void shouldReturnRent() throws Exception {
        //Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date rentDate = sdf.parse("2020-03-10");
        Date returnDate = sdf.parse("2020-03-20");

        RentDto rentDto = new RentDto(1L, 1L, 1L, rentDate, returnDate);

        when(rentService.getRentById(rentDto.getId())).thenReturn(rentDto);

        //When & Then
        mockMvc.perform(get("/v1/rents/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.carId", is(1)))
                .andExpect(jsonPath("$.rentDate", is("2020-03-10T07:00:00.000+0000")))
                .andExpect(jsonPath("$.returnDate", is("2020-03-20T07:00:00.000+0000")));
    }

    @Test
    public void shouldUpdateRentReturnDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date rentDate = sdf.parse("2020-03-10");
        Date returnDate = sdf.parse("2020-03-20");
        Date updatedReturnDate = sdf.parse("2020-04-17");

        RentDto rentDto = new RentDto(1L, 1L, 1L, rentDate, returnDate);
        RentDto updatedRentDto = new RentDto(1L, 1L, 1L, rentDate, updatedReturnDate);

        when(rentService.updateRentReturnDate(any(RentDto.class))).thenReturn(updatedRentDto);

        //Gson doesn't convert date object to json properly(?)
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(rentDto);

        mockMvc.perform(put("/v1/rents")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.carId", is(1)))
                .andExpect(jsonPath("$.rentDate", is("2020-03-10T07:00:00.000+0000")))
                .andExpect(jsonPath("$.returnDate", is("2020-04-17T07:00:00.000+0000")));
    }

    @Test
    public void shouldReturnCar() throws Exception {
        //Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date rentDate = sdf.parse("2020-03-10");
        Date returnDate = sdf.parse("2020-03-20");

        RentDto rentDto = new RentDto(1L, 1L, 1L, rentDate, returnDate);

        when(rentService.returnRentedCarById(rentDto.getId())).thenReturn(rentDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(rentDto);

        //When & Then
        mockMvc.perform(put("/v1/rents/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.carId", is(1)))
                .andExpect(jsonPath("$.rentDate", is("2020-03-10T07:00:00.000+0000")))
                .andExpect(jsonPath("$.returnDate", is("2020-03-20T07:00:00.000+0000")));
    }

    @Test
    public void shouldAddRent() throws Exception {
        //Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date rentDate = sdf.parse("2020-03-10");
        Date returnDate = sdf.parse("2020-03-20");

        Rent rent = new Rent(rentDate, returnDate);
        RentDto rentDto = new RentDto(1L, 1L, 1L, rentDate, returnDate);

        when(rentMapper.mapToRent(any(RentDto.class))).thenReturn(rent);
        when(rentService.saveRent(rentDto)).thenReturn(rent);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(rentDto);

        //When & Then
        mockMvc.perform(post("/v1/rents")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteRent() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/rents/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}