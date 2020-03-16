package com.kodilla.carsbackend.client;

import com.google.gson.Gson;
import com.kodilla.carsbackend.domain.avis.AvisQueryDto;
import com.kodilla.carsbackend.domain.avis.Location;
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
        //Given
        AvisQueryDto avisQueryDto = new AvisQueryDto("Avis", "PL", "Warsaw", "1");
        List<Location> response = avisClient.getLocations(avisQueryDto);
        Gson gson = new Gson();

        //When
        String jsonContent = gson.toJson(response);

        //Then
        System.out.println(jsonContent);
    }

    @Test
    public void testCreateAuthorization() {
        //Given & When
        String token = avisClient.createAuthorization();

        //Then
        System.out.println(token);
    }
}