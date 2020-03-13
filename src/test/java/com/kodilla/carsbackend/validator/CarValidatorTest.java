package com.kodilla.carsbackend.validator;

import com.kodilla.carsbackend.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarValidatorTest {

    @InjectMocks
    private CarValidator carValidator;

    @Mock
    private CarRepository carRepository;

    @Test
    public void testValidateRegistrationNumber() {
        //Given
        String registrationNumber = "DZA 111111";

        when(carRepository.retrieveCarsWithRegistrationNumber(registrationNumber)).thenReturn(1);

        //When
        boolean result = carValidator.validateRegistrationNumber(registrationNumber);

        //Then
        assertFalse(result);
    }
}