package com.example.carservice.repositories;

import com.example.carservice.model.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepositories extends JpaRepository<Parts, Integer> {
    List<Parts> findAllByCarVin(String vin);
}
