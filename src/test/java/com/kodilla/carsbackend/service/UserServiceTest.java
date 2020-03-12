package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.users.User;
import com.kodilla.carsbackend.domain.users.UserDto;
import com.kodilla.carsbackend.mapper.UserMapper;
import com.kodilla.carsbackend.repository.UserRepository;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetAllUsers() {
        //Given
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Test name", "Test lastname", "AAA 111111", "QWERTY", new ArrayList<>()));
        users.add(new User(2L, "Test name 1", "Test lastname 1", "AAA 222222", "ZXSPEC", new ArrayList<>()));

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L, "Test name", "Test lastname", "AAA 111111", "QWERTY"));
        userDtos.add(new UserDto(2L, "Test name 1", "Test lastname 1", "AAA 22222", "ZXSPEC"));

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.mapToUserDtoList(users)).thenReturn(userDtos);

        //When
        List<UserDto> fetchedUsersList = userService.getAllUsers();

        //Then
        assertEquals(2, fetchedUsersList.size());
    }

    @Test
    public void testGetUserById() throws Exception {
        //Given
        User user = new User(1L, "Test name", "Test lastname", "AAA 111111", "QWERTY", new ArrayList<>());
        Optional<User> optionalUser = Optional.of(user);
        UserDto userDto = new UserDto(1L, "Test name", "Test lastname", "AAA 111111", "QWERTY");

        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userRepository.findById(1L)).thenReturn(optionalUser);

        //When
        UserDto fetchedUser = userService.getUserById(1L);

        //Then
        assertEquals(1L, fetchedUser.getId().longValue());
        assertEquals("Test name", fetchedUser.getFirstName());
        assertEquals("Test lastname", fetchedUser.getLastName());
        assertEquals("AAA 111111", fetchedUser.getCardIdNumber());
        assertEquals("QWERTY", fetchedUser.getDrivingLicenseNumber());
    }
}