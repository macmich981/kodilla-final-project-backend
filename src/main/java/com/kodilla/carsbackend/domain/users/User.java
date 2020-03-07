package com.kodilla.carsbackend.domain.users;

import com.kodilla.carsbackend.domain.rents.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String cardIdNumber;

    @Column
    @NotNull
    private String drivingLicenseNumber;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    public User(String firstName, String lastName, String cardIdNumber, String drivingLicenseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardIdNumber = cardIdNumber;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
