package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.users.User;
import com.kodilla.carsbackend.domain.users.UserDto;
import com.kodilla.carsbackend.domain.users.UserNotFoundException;
import com.kodilla.carsbackend.mapper.UserMapper;
import com.kodilla.carsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public static final String ERROR_MSG = "User not found";

    public User saveUser(final UserDto userDto) {
        return userRepository.save(userMapper.mapToUser(userDto));
    }

    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public UserDto getUserById(final Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ERROR_MSG)));
    }

    public UserDto updateUser(final UserDto userDto) throws UserNotFoundException {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException(ERROR_MSG));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCardId(userDto.getCardId());
        user.setDrivingLicense(userDto.getDrivingLicense());
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public void deleteUserById(final Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ERROR_MSG));
        userRepository.delete(user);
    }

    public List<UserDto> getUsersByLastName(final String lastName) {
        return userMapper.mapToUserDtoList(userRepository.findByLastNameStartsWithIgnoreCase(lastName));
    }
}
