package ua.deti.tqs.CarsService.services;

import ua.deti.tqs.CarsService.entities.Car;
import ua.deti.tqs.CarsService.repository.CarRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CarManagerService {
    CarRepository carRepository;

    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        
        return carRepository.findById(carId);
    }
}