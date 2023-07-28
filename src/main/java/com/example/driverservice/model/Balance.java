package com.example.driverservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer red;
    private Integer green;
    private Integer blue;

    @OneToOne
    private Drivers driver;
}
