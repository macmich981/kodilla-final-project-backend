package com.kodilla.carsbackend.repository;

import com.kodilla.carsbackend.domain.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    List<User> findByLastNameStartsWithIgnoreCase(String lastName);
}
