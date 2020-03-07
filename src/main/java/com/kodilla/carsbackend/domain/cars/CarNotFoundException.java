package com.kodilla.carsbackend.domain.cars;

public class CarNotFoundException extends Exception {
    public CarNotFoundException(String message) {
        super(message);
    }
}
