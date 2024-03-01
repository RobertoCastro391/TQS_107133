package ua.deti.tqs.CarsService.Services;

import ua.deti.tqs.CarsService.Car;
import ua.deti.tqs.CarsService.Repository.CarRepository;

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
        
        Car car = carRepository.findByCarId(carId);

        if (car == null) {
            return Optional.empty();
        }
        
        return Optional.of(car);
    }
}