package com.kodilla.carsbackend.domain.rents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentDto {
    private Long id;
    private Long userId;
    private Long carId;
    private Date rentDate;
    private Date returnDate;
}
