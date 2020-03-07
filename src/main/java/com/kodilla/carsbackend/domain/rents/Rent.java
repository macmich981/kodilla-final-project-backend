package com.kodilla.carsbackend.domain.rents;

import com.kodilla.carsbackend.domain.cars.Car;
import com.kodilla.carsbackend.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RENTS")
public class Rent {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "CAR_COPY_ID")
    private Car car;

    @NotNull
    @Column
    private Date rentDate;

    @NotNull
    @Column
    private Date returnDate;

    public Rent(Date rentDate, Date returnDate) {
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}
