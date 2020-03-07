package com.kodilla.carsbackend.controller;

import com.kodilla.carsbackend.domain.carbrands.CarBrandNotFoundException;
import com.kodilla.carsbackend.domain.cars.CarDto;
import com.kodilla.carsbackend.domain.cars.CarNotFoundException;
import com.kodilla.carsbackend.domain.cars.CarRegistrationNumberException;
import com.kodilla.carsbackend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.POST, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public void addCar(@RequestBody CarDto carDto) throws CarBrandNotFoundException, CarRegistrationNumberException {
        carService.saveCar(carDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars/{carId}")
    public CarDto getCarById(@PathVariable Long carId) throws CarNotFoundException {
        return carService.getCarById(carId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cars/{carId}")
    public CarDto updateCarStateToDamaged(@PathVariable Long carId) throws CarNotFoundException {
        return carService.updateCarStateToDamaged(carId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cars/{carId}")
    public void deleteCarCopy(@PathVariable Long carId) throws CarNotFoundException {
        carService.deleteCarById(carId);
    }
}
