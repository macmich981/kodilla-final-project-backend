package com.kodilla.carsbackend.mapper;

import com.kodilla.carsbackend.domain.rents.Rent;
import com.kodilla.carsbackend.domain.rents.RentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {
    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(rentDto.getRentDate(), rentDto.getReturnDate());
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getUser().getId(),
                rent.getCar().getId(),
                rent.getRentDate(),
                rent.getReturnDate()
        );
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(r ->
                        new RentDto(
                                r.getId(),
                                r.getUser().getId(),
                                r.getCar().getId(),
                                r.getRentDate(),
                                r.getReturnDate()))
                .collect(Collectors.toList());
    }
}
