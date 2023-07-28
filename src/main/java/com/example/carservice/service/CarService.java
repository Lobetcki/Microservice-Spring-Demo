package com.example.carservice.service;

import com.example.carservice.dto.CarDTO;
import com.example.carservice.dto.PartDTO;
import com.example.carservice.enams.CarsSort;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public interface CarService {

    //1 Создание авто
    CarDTO createCar(CarDTO carDTO);

    //2 Информация о авто
    CarDTO getCarById(String vin);

    //3 Изменить информацию об авто
    CarDTO updateCar(CarDTO carDTO);

    //4 Удалить авто
    boolean deleteCar(String vin);

    //5 Получение всех авто
    List<CarDTO> getAllCars(Integer page, Integer size, CarsSort sortBy);

    //6 Установка или замена деталей в авто
    boolean installParts(String vin, PartDTO part);

    //7 Получение всех деталей авто
    List<PartDTO> getCarParts(String vin);

    //8 Замена деталей в авто
    boolean ReplaceParts(String vin, Integer partId, PartDTO partDTO);

    //9 Поиск авто
    List<CarDTO> searchCars(String vin, String manufacturer, String brand, Year year, String stateNumber);

    // Получение информации о водителе авто
    Integer getCarDriver(String vin);

    // Назначение авто для водителя
    boolean appointDriver(String vin,int driverId);
}
