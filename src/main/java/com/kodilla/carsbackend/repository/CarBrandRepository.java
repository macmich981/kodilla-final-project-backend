package com.kodilla.carsbackend.repository;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarBrandRepository extends CrudRepository<CarBrand, Long> {
    @Override
    List<CarBrand> findAll();
}
