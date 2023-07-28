package com.example.carservice.dto;

import com.example.carservice.model.Cars;
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

    public static CarDTO fromCarDTO(Cars cars) {
        CarDTO carDTO = new CarDTO();
        carDTO.setVin(cars.getVin());
        carDTO.setStateNumber(cars.getStateNumber());
        carDTO.setManufacturer(cars.getManufacturer());
        carDTO.setBrand(cars.getBrand());
        carDTO.setYearRelease(cars.getYearRelease());
        return carDTO;
    }

    public Cars toCar() {
        Cars cars = new Cars();
        cars.setVin(this.getVin());
        cars.setStateNumber(this.getStateNumber());
        cars.setManufacturer(this.getManufacturer());
        cars.setBrand(this.getBrand());
        cars.setYearRelease(this.getYearRelease());
        return cars;
    }
}
