package com.example.carservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.List;

@Entity
@Getter
@Setter
public class Cars {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String vin;
    private String stateNumber;

    private String manufacturer;
    private String brand;
    private Year yearRelease;

    private Integer driver;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Parts> parts;
}
