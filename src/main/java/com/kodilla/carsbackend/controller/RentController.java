package com.kodilla.carsbackend.controller;

import com.kodilla.carsbackend.domain.cars.CarNotFoundException;
import com.kodilla.carsbackend.domain.rents.RentDateException;
import com.kodilla.carsbackend.domain.rents.RentDto;
import com.kodilla.carsbackend.domain.rents.RentNotFoundException;
import com.kodilla.carsbackend.domain.users.UserNotFoundException;
import com.kodilla.carsbackend.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class RentController {
    @Autowired
    private RentService rentService;

    @RequestMapping(method = RequestMethod.POST, value = "/rents", consumes = APPLICATION_JSON_VALUE)
    public void addRent(@RequestBody RentDto rentDto) throws UserNotFoundException, CarNotFoundException, RentDateException {
        rentService.saveRent(rentDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rents")
    public List<RentDto> getAllRents() {
        return rentService.getAllRents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rents/{rentId}")
    public RentDto getRentById(@PathVariable Long rentId) throws RentNotFoundException {
        return rentService.getRentById(rentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rents")
    public RentDto updateRentReturnDate(@RequestBody RentDto rentDto) throws RentNotFoundException, RentDateException {
        return rentService.updateRentReturnDate(rentDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rents/{rentId}")
    public void deleteRent(@PathVariable Long rentId) throws RentNotFoundException {
        rentService.deleteById(rentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rents/{rentId}")
    public RentDto returnCar(@PathVariable Long rentId) throws RentNotFoundException {
        return rentService.returnRentedCarById(rentId);
    }
}
