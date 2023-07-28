package com.example.driverservice.service.serviceImpl;

import com.example.driverservice.dto.BalanceDTO;
import com.example.driverservice.dto.DriverDTO;
import com.example.driverservice.exception.IdNotFoundException;
import com.example.driverservice.model.Drivers;
import com.example.driverservice.repositories.DriverRepositories;
import com.example.driverservice.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepositories driverRepositories;


    public DriverServiceImpl(DriverRepositories driverRepositories) {
        this.driverRepositories = driverRepositories;
    }

    @Override
    public List<DriverDTO> getAllDrivers(Integer page, Integer size, String sortBy) {
        return null;
    }

    //1 Создание водителя
    @Override
    public DriverDTO createDriver(DriverDTO driverDTO) {
        driverRepositories.save(driverDTO.toDriver());
        return driverDTO;
    }

    //2 Информация о водителе
    @Override
    public DriverDTO getDriverById(Integer id) {
        Drivers drivers = driverRepositories.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return DriverDTO.fromDriverDTO(drivers);
    }

    //3 Изменить информацию водителе
    @Override
    public DriverDTO updateDriver(Integer id, DriverDTO driverDTO) {
        Drivers drivers = driverDTO.toDriver();
        drivers.setId(id);
        driverRepositories.save(drivers);
        return driverDTO;
    }

    //4 Удалить водителя
    @Override
    public boolean deleteDriver(Integer id) {
        driverRepositories.deleteById(id);
        return true;
    }

    //     5 Информация об авто водителя
    @Override
    public String getDriverCar(Integer driverId) {
        return driverRepositories.findById(driverId)
                .orElseThrow(IdNotFoundException::new)
                .getCarsVIN();
    }

    // 6 Назначение авто для водителя
    @Override
    public boolean assignCarToDriver(Integer driverId, String vin) {
        Drivers driver = driverRepositories.findById(driverId).orElseThrow(IdNotFoundException::new);
        if (driver.getId().equals(driverId)) {
            driver.setCarsVIN(vin);
            driverRepositories.save(driver);
            return true;
        }
        return false;
    }
}
