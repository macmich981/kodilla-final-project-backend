package com.kodilla.carsbackend.client;

import com.google.gson.Gson;
import com.kodilla.carsbackend.domain.AvisResponse;
import com.kodilla.carsbackend.domain.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvisClientTest {

    @Autowired
    private AvisClient avisClient;

    @Test
    public void testGetLocations() {
        //Given & When
        List<Location> response = avisClient.getLocations("Avis", "PL", "Warsaw", "1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(response);


        //Then
        System.out.println(jsonContent);
    }
}