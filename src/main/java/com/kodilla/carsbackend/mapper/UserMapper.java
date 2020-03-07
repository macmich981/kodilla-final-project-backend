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
                userDto.getCardId(),
                userDto.getDrivingLicense());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCardId(),
                user.getDrivingLicense());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(u ->
                        new UserDto(u.getId(),
                                u.getFirstName(),
                                u.getLastName(),
                                u.getCardId(),
                                u.getDrivingLicense()))
                .collect(Collectors.toList());
    }
}
