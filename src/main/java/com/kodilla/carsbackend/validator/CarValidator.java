package com.kodilla.carsbackend.validator;

import com.kodilla.carsbackend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarValidator {
    @Autowired
    private CarRepository carRepository;

    public boolean validateRegistrationNumber(String registrationNumber) {
        int count = carRepository.retrieveCarsWithRegistrationNumber(registrationNumber);
        return count <= 0;
    }
}
