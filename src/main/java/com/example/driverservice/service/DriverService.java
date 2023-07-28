package com.example.driverservice.service;

import com.example.driverservice.dto.BalanceDTO;
import com.example.driverservice.dto.DriverDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface DriverService {
    List<DriverDTO> getDriversByCarId(Long carId);

    List<DriverDTO> getAllDrivers(Integer page, Integer size, String sortBy);

    //1 Создание водителя
    DriverDTO createDriver(DriverDTO driverDTO);

    //2 Информация о водителе
    DriverDTO getDriverById(Integer id);

    //3 Изменить информацию водителе
    DriverDTO updateDriver(Integer id, DriverDTO driverDTO);

    //4 Удалить водителя
    boolean deleteDriver(Integer id);

//    //5 Информация об авто водителя
//    List<CarDTO> getDriverCars(Integer id);

    // 6 Назначение авто для водителя
    boolean assignCarToDriver(Integer driverId, String vin);

    BalanceDTO getBalance(Long driverId, String currency);

    //     5 Информация об авто водителя
    String getDriverCar(Integer driverId);
}
