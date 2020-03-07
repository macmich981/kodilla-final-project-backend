package com.kodilla.carsbackend.domain.cars;

import com.kodilla.carsbackend.domain.carbrands.CarBrand;
import com.kodilla.carsbackend.domain.rents.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(name = "Car.retrieveCarsWithRegistrationNumber",
        query = "select count(*) from cars c where c.registration_number=:car_registration_number")

@NamedNativeQuery(name = "Car.retrieveCarsWithState",
        query = "select count(*) from cars c where c.state=:car_state")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARS")
public class Car {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @NotNull
    @Column
    private String registrationNumber;

    @NotNull
    @Column
    private int productionYear;

    @NotNull
    @Column
    private String state;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Rent rent;

    @ManyToOne
    @JoinColumn(name = "CAR_BRAND_ID")
    private CarBrand carBrand;

    public Car(String registrationNumber, int productionYear) {
        this.registrationNumber = registrationNumber;
        this.productionYear = productionYear;
        this.state = State.AVAILABLE.name();
    }
}
