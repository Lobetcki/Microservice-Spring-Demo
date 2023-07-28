package com.example.driverservice.model;

import lombok.Getter;

@Getter
public enum Currency {

    RED,
    GREEN,
    BLUE;

    private final Double redOfGreen = 2.5;
    private final Double greenOfBlue = 0.6;
    private final Double redOfBlue = 1.5;

    Currency() {
    }
}
