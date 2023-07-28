package com.example.driverservice.controller;

import com.example.driverservice.dto.BalanceDTO;
import com.example.driverservice.dto.CarDTO;
import com.example.driverservice.dto.DriverDTO;
import com.example.driverservice.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriversController {

    private final DriverService driverService;
    private RestTemplate restTemplate;

    public DriversController(DriverService driverService) {
        this.driverService = driverService;
    }

    //1 Создание водителя
    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) {
        DriverDTO createdDriver = driverService.createDriver(driverDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDriver);
    }

    //2 Информация о водителе
    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Integer id) {
        DriverDTO driverDTO = driverService.getDriverById(id);
        if (driverDTO != null) {
            return ResponseEntity.ok(driverDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //3 Изменить информацию водителе
    @PutMapping("/{driverId}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable Integer id,
                                                  @RequestBody DriverDTO driverDTO) {
        DriverDTO updatedDriver = driverService.updateDriver(id, driverDTO);
        if (updatedDriver != null) {
            return ResponseEntity.ok(updatedDriver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //4 Удалить водителя
    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        boolean deleted = driverService.deleteDriver(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    //7 Соотояние счета водителя в определенной валюте
    @GetMapping("/{driverId}/balance/{currency}")
    public ResponseEntity<BalanceDTO> getDriverBalance(@PathVariable Long driverId,
                                                       @PathVariable String currency) {
        BalanceDTO balanceDTO = driverService.getBalance(driverId, currency);
        if (balanceDTO != null) {
            return ResponseEntity.ok(balanceDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    8 Получение всех водителей
    @GetMapping("/all")
    public ResponseEntity<List<DriverDTO>> getAllDrivers(
            @RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "size", defaultValue = "20") @Min(1) @Max(100) Integer size,
            @RequestParam(required = false) String sortBy) {
        List<DriverDTO> drivers = driverService.getAllDrivers(page, size, sortBy);
        if (!drivers.isEmpty()) {
            return ResponseEntity.ok(drivers);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

//     5 Информация об авто водителя
    @GetMapping("/{driverId}/cars")
    public ResponseEntity<CarDTO> getDriverCars(@PathVariable Integer driverId) {
        String vin = driverService.getDriverCar(driverId);

        // Выполняем HTTP GET запрос к Сервису Cars для получения информации о авто
        String url = "http://localhost:8765/driver/" + vin;
        ResponseEntity<CarDTO> response = restTemplate.getForEntity(url, CarDTO.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        }
        return ResponseEntity.status(response.getStatusCode()).build();
    }

    // 6 Назначение авто для водителя
    @PostMapping("/{carId}/cars/{driverId}")
    public ResponseEntity<Void> assignCarToDriver(@PathVariable Integer driverId,
                                                  @PathVariable String vin) {
        boolean assigned = driverService.assignCarToDriver(driverId, vin);
        if (assigned) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
