package com.kodilla.carsbackend.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RentValidatorTest {

    @InjectMocks
    private RentValidator rentValidator;

    @Test
    public void testRentValidator() throws Exception {
        //Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date rentDate = sdf.parse("2020/03/13");
        Date returnDate = sdf.parse("2020/03/17");

        //When
        boolean result = rentValidator.validateDate(rentDate, returnDate);

        //Then
        assertTrue(result);
    }
}