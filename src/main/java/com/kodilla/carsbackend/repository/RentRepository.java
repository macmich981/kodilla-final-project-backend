package com.kodilla.carsbackend.repository;

import com.kodilla.carsbackend.domain.rents.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();
}
