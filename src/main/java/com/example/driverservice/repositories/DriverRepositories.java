package com.example.driverservice.repositories;

import com.example.driverservice.model.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepositories extends JpaRepository<Drivers, Integer> {


}
