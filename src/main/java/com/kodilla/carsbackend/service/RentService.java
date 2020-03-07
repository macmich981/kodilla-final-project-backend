package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.domain.cars.Car;
import com.kodilla.carsbackend.domain.cars.CarNotFoundException;
import com.kodilla.carsbackend.domain.cars.State;
import com.kodilla.carsbackend.domain.rents.Rent;
import com.kodilla.carsbackend.domain.rents.RentDateException;
import com.kodilla.carsbackend.domain.rents.RentDto;
import com.kodilla.carsbackend.domain.rents.RentNotFoundException;
import com.kodilla.carsbackend.domain.users.User;
import com.kodilla.carsbackend.domain.users.UserNotFoundException;
import com.kodilla.carsbackend.mapper.RentMapper;
import com.kodilla.carsbackend.repository.CarRepository;
import com.kodilla.carsbackend.repository.RentRepository;
import com.kodilla.carsbackend.repository.UserRepository;
import com.kodilla.carsbackend.validator.RentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private RentValidator rentValidator;

    public static final String ERROR_MSG = "Rent not found";

    public Rent saveRent(final RentDto rentDto) throws UserNotFoundException, CarNotFoundException, RentDateException {
        User user = userRepository.findById(rentDto.getUserId()).orElseThrow(() -> new UserNotFoundException(UserService.ERROR_MSG));
        Car car = carRepository.findById(rentDto.getCarId()).orElseThrow(() -> new CarNotFoundException(CarService.ERROR_MSG));
        if (car.getState().equals("RENTED") || car.getState().equals("DAMAGED")) {
            throw new CarNotFoundException(CarService.ERROR_MSG);
        }
        if (!rentValidator.validateDate(rentDto.getRentDate(), rentDto.getReturnDate())) {
            throw new RentDateException("Invalid rent date");
        }
        Rent rent = rentMapper.mapToRent(rentDto);
        user.getRents().add(rent);
        rent.setUser(user);
        rent.setCar(car);
        car.setState(State.RENTED.name());
        car.setRent(rent);
        return rentRepository.save(rent);
    }

    public List<RentDto> getAllRents() {
        return rentMapper.mapToRentDtoList(rentRepository.findAll());
    }

    public RentDto getRentById(final Long id) throws RentNotFoundException {
        return rentMapper.mapToRentDto(rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException(ERROR_MSG)));
    }

    public RentDto updateRentReturnDate(final RentDto rentDto) throws RentNotFoundException, RentDateException {
        Rent rent = rentRepository.findById(rentDto.getId()).orElseThrow(() -> new RentNotFoundException(ERROR_MSG));
        if (!rentValidator.validateDate(rentDto.getRentDate(), rentDto.getReturnDate())) {
            throw new RentDateException("Invalid rent date");
        }
        rent.setReturnDate(rentDto.getReturnDate());
        return rentMapper.mapToRentDto(rentRepository.save(rent));
    }

    public void deleteById(final Long id) throws RentNotFoundException {
        Rent rent = rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException(ERROR_MSG));
        rentRepository.delete(rent);
    }

    public RentDto returnRentedCarById(final Long id) throws RentNotFoundException {
        Rent rent = rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException(ERROR_MSG));
        rent.setReturnDate(new Date());
        rent.getCar().setState(State.AVAILABLE.name());
        return rentMapper.mapToRentDto(rent);
    }
}
