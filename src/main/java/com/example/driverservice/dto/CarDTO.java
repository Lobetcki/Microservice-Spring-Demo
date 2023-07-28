package com.example.driverservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class CarDTO {
    private String vin;
    private String stateNumber;

    private String manufacturer;
    private String brand;
    private Year yearRelease;
}
