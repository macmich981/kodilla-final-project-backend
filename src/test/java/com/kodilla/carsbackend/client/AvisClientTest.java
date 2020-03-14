package com.kodilla.carsbackend.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvisClientTest {

    @Autowired
    private AvisClient avisClient;

    @Test
    public void testGetLocations() {
        //Given & When
        String response = avisClient.getLocations("Avis", "PL", "Warsaw", "1");

        //Then
        System.out.println(response);
    }
}