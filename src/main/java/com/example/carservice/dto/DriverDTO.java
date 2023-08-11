package com.example.carservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DriverDTO {

    @JsonIgnore
    private Integer id;
    private String fullName;
    private String passport;
    private String category;
    private LocalDate dateOfBirth;
    private Integer experience;


}
