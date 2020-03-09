package com.kodilla.carsbackend.controller;

import com.google.gson.Gson;
import com.kodilla.carsbackend.domain.users.User;
import com.kodilla.carsbackend.domain.users.UserDto;
import com.kodilla.carsbackend.mapper.UserMapper;
import com.kodilla.carsbackend.service.UserService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void shouldReturnUsers() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L, "Jan", "Kowalski", "ATR 123456", "QWERTY"));
        userDtos.add(new UserDto(2L, "Adam", "Nowak", "ATY 987654", "ZXCVBN"));

        when(userService.getAllUsers()).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Jan")))
                .andExpect(jsonPath("$[0].lastName", is("Kowalski")))
                .andExpect(jsonPath("$[0].cardIdNumber", is("ATR 123456")))
                .andExpect(jsonPath("$[0].drivingLicenseNumber", is("QWERTY")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Adam")))
                .andExpect(jsonPath("$[1].lastName", is("Nowak")))
                .andExpect(jsonPath("$[1].cardIdNumber", is("ATY 987654")))
                .andExpect(jsonPath("$[1].drivingLicenseNumber", is("ZXCVBN")));
    }

    @Test
    public void shouldReturnUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "ATX 123456", "QWERTY");

        when(userService.getUserById(userDto.getId())).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Jan")))
                .andExpect(jsonPath("$.lastName", is("Kowalski")))
                .andExpect(jsonPath("$.cardIdNumber", is("ATX 123456")))
                .andExpect(jsonPath("$.drivingLicenseNumber", is("QWERTY")));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "ATX 123456", "QWERTY");
        UserDto updatedUserDto = new UserDto(1L, "Jan", "Kowalski", "DFV 567890", "QWERTY");

        when(userService.updateUser(any(UserDto.class))).thenReturn(updatedUserDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Jan")))
                .andExpect(jsonPath("$.lastName", is("Kowalski")))
                .andExpect(jsonPath("$.cardIdNumber", is("DFV 567890")))
                .andExpect(jsonPath("$.drivingLicenseNumber", is("QWERTY")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        User user = new User(1L, "Jan", "Kowalski", "ATX 123456", "QWERTY", new ArrayList<>());
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "ATX 123456", "QWERTY");

        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(userService.saveUser(userDto)).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnUsersByLastName() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L, "Jan", "Kowalski", "ATX 123456", "QWERTY"));
        userDtos.add(new UserDto(2L, "Adam", "Kowalski", "ATY 987654", "ZXCVBN"));

        when(userService.getUsersByLastName("kowalski")).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/v1/users/getUsersByLastName?lastName=kowalski")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Jan")))
                .andExpect(jsonPath("$[0].lastName", is("Kowalski")))
                .andExpect(jsonPath("$[0].cardIdNumber", is("ATX 123456")))
                .andExpect(jsonPath("$[0].drivingLicenseNumber", is("QWERTY")))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Adam")))
                .andExpect(jsonPath("$[1].lastName", is("Kowalski")))
                .andExpect(jsonPath("$[1].cardIdNumber", is("ATY 987654")))
                .andExpect(jsonPath("$[1].drivingLicenseNumber", is("ZXCVBN")));
    }
}