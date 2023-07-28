package com.example.driverservice.dto;

import com.example.driverservice.model.Drivers;
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


    public Drivers toDriver() {
        Drivers drivers = new Drivers();
        drivers.setFullName(this.getFullName());
        drivers.setPassport(this.getPassport());
        drivers.setRightsCategory(this.getCategory());
        drivers.setDateOfBirth(this.getDateOfBirth());
        drivers.setExperience(this.getExperience());
        return drivers;
    }

    public static DriverDTO fromDriverDTO(Drivers drivers) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(drivers.getId());
        driverDTO.setFullName(drivers.getFullName());
        driverDTO.setPassport(drivers.getPassport());
        driverDTO.setCategory(drivers.getRightsCategory());
        driverDTO.setDateOfBirth(drivers.getDateOfBirth());
        driverDTO.setExperience(drivers.getExperience());
        return driverDTO;
    }
}
