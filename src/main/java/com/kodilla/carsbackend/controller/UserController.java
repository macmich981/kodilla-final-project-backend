package com.kodilla.carsbackend.controller;

import com.kodilla.carsbackend.domain.users.UserDto;
import com.kodilla.carsbackend.domain.users.UserNotFoundException;
import com.kodilla.carsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(userDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
    }
}
