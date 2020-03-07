package com.kodilla.carsbackend.validator;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RentValidator {
    public boolean validateDate(Date rentDate, Date returnDate) {
        return !rentDate.after(returnDate);
    }
}
