package com.kodilla.carsbackend.domain.carbrands;

import com.kodilla.carsbackend.domain.cars.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CAR_BRANDS")
public class CarBrand {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    private String brandName;

    @Column
    @NotNull
    private int constructionYear;

    @OneToMany(
            mappedBy ="carBrand",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Car> carCopies = new ArrayList<>();

    public CarBrand(String brandName, int constructionYear) {
        this.brandName = brandName;
        this.constructionYear = constructionYear;
    }
}
