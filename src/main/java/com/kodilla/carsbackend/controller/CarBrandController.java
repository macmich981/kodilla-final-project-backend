package com.kodilla.carsbackend.controller;

import com.kodilla.carsbackend.domain.carbrands.CarBrandDto;
import com.kodilla.carsbackend.domain.carbrands.CarBrandNotFoundException;
import com.kodilla.carsbackend.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    @RequestMapping(method = RequestMethod.POST, value = "/carbrands", consumes = APPLICATION_JSON_VALUE)
    public void addCarBrand(@RequestBody CarBrandDto carBrandDto) {
        carBrandService.saveCarBrand(carBrandDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/carbrands")
    public List<CarBrandDto> getAllCarBrands() {
        return carBrandService.getAllCarBrands();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/carbrands/{carBrandId}")
    public CarBrandDto getCarBrandById(@PathVariable Long carBrandId) throws CarBrandNotFoundException {
        return carBrandService.getCarBrandById(carBrandId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/carbrands/{carBrandId}")
    public void deleteCarBrand(@PathVariable Long carBrandId) throws CarBrandNotFoundException {
        carBrandService.deleteCarBrandById(carBrandId);
    }
}
