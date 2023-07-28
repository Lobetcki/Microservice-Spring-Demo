package com.example.driverservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String passport;
    private String rightsCategory;
    private LocalDate dateOfBirth;
    private Integer experience;

    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    private String carsVIN;
}
