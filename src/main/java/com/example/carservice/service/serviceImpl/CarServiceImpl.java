package com.example.carservice.service.serviceImpl;

import com.example.carservice.dto.CarDTO;
import com.example.carservice.dto.PartDTO;
import com.example.carservice.enams.CarsSort;
import com.example.carservice.exception.IdNotFoundException;
import com.example.carservice.model.Cars;
import com.example.carservice.model.Parts;
import com.example.carservice.repositories.CarRepositories;
import com.example.carservice.repositories.PartRepositories;
import com.example.carservice.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Year;
//import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.carservice.specifications.CarSpecifications.*;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepositories carRepositories;
    private final PartRepositories partRepositories;

    private RestTemplate restTemplate;

    public CarServiceImpl(CarRepositories carRepositories,
                          PartRepositories partRepositories) {
        this.carRepositories = carRepositories;
        this.partRepositories = partRepositories;
    }

    //1 Создание авто
    @Override
    public CarDTO createCar(CarDTO carDTO) {
        carRepositories.save(carDTO.toCar());
        return carDTO;
    }

    //2 Информация о авто
    @Override
    public CarDTO getCarById(String vin) {
        Cars car = carRepositories.findById(vin)
                .orElseThrow(IdNotFoundException::new);
        return CarDTO.fromCarDTO(car);
    }

    //3 Изменить информацию об авто
    @Override
    public CarDTO updateCar(CarDTO carDTO) {
        Cars car = carDTO.toCar();
        carRepositories.save(car);
        return carDTO;
    }

    //4 Удалить авто
    @Override
    public boolean deleteCar(String vin) {
        carRepositories.deleteById(vin);
        return true;
    }

    //5 Получение всех авто
    @Override
    public List<CarDTO> getAllCars(Integer page, Integer size, CarsSort sortBy) {
        Page<Cars> cars = carRepositories
                .findAll(PageRequest.of(page, size, sortBy.getSort()));
        return cars.stream()
                .map(CarDTO::fromCarDTO).toList();
    }

    //6 Установка или замена деталей в авто
    @Override
    public boolean installParts(String vin, PartDTO partDTO) {
        Cars car = carRepositories.findById(vin).orElseThrow(IdNotFoundException::new);
        Parts part = partDTO.toPart();
        part.setCar(car);
        partRepositories.save(part);
        return true;
    }

    //7 получение всех деталей авто
    @Override
    public List<PartDTO> getCarParts(String vin) {
        List<Parts> parts = partRepositories.findAllByCarVin(vin);
        return parts.stream().map(PartDTO::fromPartDTO).collect(Collectors.toList());
    }

    //8 Замена деталей в авто
    @Override
    public boolean ReplaceParts(String vin, Integer partId, PartDTO partDTO) {
        Parts part = partRepositories.findById(partId).orElseThrow(IdNotFoundException::new);
        if (!part.getCar().getVin().equals(vin)) return false;
        part.setSerialNumber(partDTO.getSerialNumber());
        part.setNamePart(partDTO.getNamePart());
        partRepositories.save(part);
        return true;
    }

    //9 Поиск авто
    @Override
    public List<CarDTO> searchCars(String vin,
                                   String manufacturer,
                                   String brand,
                                   Year year,
                                   String stateNumber) {
        List<Cars> cars = carRepositories.findAll(Specification.where(
                byVin(vin)
                        .and(byManufacturer(manufacturer))
                        .and(byBrand(brand))
                        .and(byYear(year))
                        .and(byStateNumber(stateNumber))
        ));
        return cars.stream().map(CarDTO::fromCarDTO).collect(Collectors.toList());
    }

    // Получение информации о водителе авто
    @Override
    public Integer getCarDriver(String vin) {
        return carRepositories.findById(vin)
                .orElseThrow(IdNotFoundException::new)
                .getDriver();
    }

    @Override
    public boolean appointDriver(String vin, int driverId) {
        Cars car = carRepositories.findById(vin).orElseThrow(IdNotFoundException::new);
        if (!car.getVin().equals(vin)) return false;
        car.setDriver(driverId);
        carRepositories.save(car);
        return true;
    }
}
