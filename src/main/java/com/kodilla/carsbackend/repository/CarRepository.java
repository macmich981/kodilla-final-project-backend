package com.kodilla.carsbackend.repository;

import com.kodilla.carsbackend.domain.cars.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    @Override
    List<Car> findAll();

    @Query(nativeQuery = true)
    int retrieveCarsWithRegistrationNumber(@Param("car_registration_number") String registration_number);

    @Query(nativeQuery = true)
    int retrieveCarsWithState(@Param("car_state") String state);
}
