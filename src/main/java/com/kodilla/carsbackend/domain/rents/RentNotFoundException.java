package com.kodilla.carsbackend.domain.rents;

public class RentNotFoundException extends Exception{
    public RentNotFoundException(String message) {
        super(message);
    }
}
