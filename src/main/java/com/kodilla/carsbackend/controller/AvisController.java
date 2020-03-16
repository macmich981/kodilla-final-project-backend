package com.kodilla.carsbackend.controller;

import com.kodilla.carsbackend.client.AvisClient;
import com.kodilla.carsbackend.domain.avis.AvisQueryDto;
import com.kodilla.carsbackend.domain.avis.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/avis")
public class AvisController {

    @Autowired
    private AvisClient avisClient;

    @RequestMapping(method = RequestMethod.GET, value = "/locations")
    public List<Location> getLocations(@RequestBody AvisQueryDto avisQueryDto) {
        return avisClient.getLocations(avisQueryDto);
    }
}
