package com.example.carservice.enams;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum CarsSort {

    VIN(Sort.by("vin")),
    STATE_NUMBER(Sort.by("stateNumber")),
    MANUFACTURER(Sort.by("manufacturer")),
    BRAND(Sort.by("brand")),
    YEAR_RELEASE(Sort.by("yearRelease"));

    private final Sort sort;
}
