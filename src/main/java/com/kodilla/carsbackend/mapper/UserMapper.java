package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.users.User;
import com.kodilla.carsbackend.domain.users.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getCardIdNumber(),
                userDto.getDrivingLicenseNumber());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCardIdNumber(),
                user.getDrivingLicenseNumber());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(u ->
                        new UserDto(u.getId(),
                                u.getFirstName(),
                                u.getLastName(),
                                u.getCardIdNumber(),
                                u.getDrivingLicenseNumber()))
                .collect(Collectors.toList());
    }
}
