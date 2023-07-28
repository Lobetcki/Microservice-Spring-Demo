package com.example.carservice.controller;

import com.example.carservice.dto.CarDTO;
import com.example.carservice.dto.DriverDTO;
import com.example.carservice.dto.PartDTO;
import com.example.carservice.enams.CarsSort;
import com.example.carservice.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarsController {

    private final CarService carService;
    private RestTemplate restTemplate;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    //1 Создание авто
    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        CarDTO createdCar = carService.createCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    //2 Информация о авто
    @GetMapping("/{vin}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable String vin) {
        CarDTO carDTO = carService.getCarById(vin);
        if (carDTO != null) {
            return ResponseEntity.ok(carDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //3 Изменить информацию об авто
    @PutMapping
    public ResponseEntity<CarDTO> updateCar(@RequestBody CarDTO carDTO) {
        CarDTO updatedCar = carService.updateCar(carDTO);
        if (updatedCar != null) {
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //4 Удалить авто
    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> deleteCar(@PathVariable String vin) {
        boolean deleted = carService.deleteCar(vin);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //5 Получение всех авто
    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars(
            @RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "size", defaultValue = "20") @Min(1) @Max(100) Integer size,
            @RequestParam(required = false) CarsSort sortBy) {
        List<CarDTO> cars = carService.getAllCars(page, size, sortBy);
        if (!cars.isEmpty()) {
            return ResponseEntity.ok(cars);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //6 Установка деталей в авто
    @PostMapping("/{vin}/parts")
    public ResponseEntity installParts(
            @PathVariable String vin,
            @RequestBody PartDTO partDTO) {
        boolean updated = carService.installParts(vin, partDTO);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //7 получение всех деталей авто
    @GetMapping("/{vin}/all_parts")
    public ResponseEntity<List<PartDTO>> getCarParts(String vin) {
        List<PartDTO> cars = carService.getCarParts(vin);
        if (!cars.isEmpty()) {
            return ResponseEntity.ok(cars);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //8 Замена деталей в авто
    @PutMapping("/{vin}/parts/{partId}")
    public ResponseEntity<Void> ReplaceParts(
            @PathVariable String vin,
            @PathVariable Integer partId,
            @RequestBody PartDTO partDTO) {
        boolean updated = carService.ReplaceParts(vin, partId, partDTO);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //9 Поиск авто
    @GetMapping("/search")
    public ResponseEntity<List<CarDTO>> searchCars(@RequestParam(required = false) String vin,
                                                   @RequestParam(required = false) String manufacturer,
                                                   @RequestParam(required = false) String brand,
                                                   @RequestParam(required = false) Year year,
                                                   @RequestParam(required = false) String stateNumber) {
        List<CarDTO> cars = carService.searchCars(vin, manufacturer, brand, year, stateNumber);
        if (!cars.isEmpty()) {
            return ResponseEntity.ok(cars);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // 10 Получение информации о водителе авто
    @GetMapping("/{vin}/driver")
    public ResponseEntity<DriverDTO> getCarDriver(@PathVariable String vin) {
        Integer driverId = carService.getCarDriver(vin);

        // Выполняем HTTP GET запрос к Сервису Drivers для получения информации о водителе
        String url = "http://localhost:8765/driver/" + driverId;
        ResponseEntity<DriverDTO> response = restTemplate.getForEntity(url, DriverDTO.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        }
        return ResponseEntity.status(response.getStatusCode()).build();
    }

    // Назначение авто для водителя
    @PostMapping("/{carId}/driver/{driverId}")
    public ResponseEntity<Void> assignCarDriver(@PathVariable String vin,
                                                @PathVariable int driverId) {
        // Выполняем HTTP POST запрос к Сервису Drivers для установки владения водителем автомобилем
        String url = "http://localhost:8765/driver/" + vin + "/cars/" + driverId;
        ResponseEntity<Void> response = restTemplate.postForEntity(url, null, Void.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            boolean appoint = carService.appointDriver(vin, driverId);
            if (!appoint) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(response.getStatusCode()).build();
    }

}



