package com.example.carservice.repositories;

import com.example.carservice.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepositories extends JpaRepository<Cars, String>, JpaSpecificationExecutor<Cars> {

//    @Query("SELECT ")
//    Optional<Integer> findDriverById(String vin);

//    List<Cars> searchCars(String vin, String manufacturer, String brand, Integer year, String stateNumber);
}
