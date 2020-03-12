package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.users.User;
import com.kodilla.carsbackend.domain.users.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Test
    public void testMapToUser() {
        //Given
        UserDto userDto = new UserDto(1L, "Test name", "Test lastname", "AAA 000000", "QWERTY");

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertNull(user.getId());
        assertEquals("Test name", user.getFirstName());
        assertEquals("Test lastname", user.getLastName());
        assertEquals("AAA 000000", user.getCardIdNumber());
        assertEquals("QWERTY", user.getDrivingLicenseNumber());
    }

    @Test
    public void testMapToUserDto() {
        //Given
        User user = new User(1L, "Test name", "Test lastname", "AAA 000000", "QWERTY", new ArrayList<>());

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(1L, userDto.getId().longValue());
        assertEquals("Test name", userDto.getFirstName());
        assertEquals("Test lastname", userDto.getLastName());
        assertEquals("AAA 000000", userDto.getCardIdNumber());
        assertEquals("QWERTY", userDto.getDrivingLicenseNumber());
    }

    @Test
    public void testMapToUserDtoList() {
        //Given
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Test name", "Test lastname", "AAA 000000", "QWERTY", new ArrayList<>()));
        users.add(new User(2L, "Test name 1", "Test lastname 2", "AAA 000000", "QWERTY", new ArrayList<>()));

        //When
        List<UserDto> userDtos = userMapper.mapToUserDtoList(users);

        //Then
        assertEquals(2, userDtos.size());
    }
}