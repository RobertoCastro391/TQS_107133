package ua.deti.tqs.CarsService.controllers;

import java.lang.module.ResolutionException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.deti.tqs.CarsService.entities.Car;
import ua.deti.tqs.CarsService.services.CarManagerService;

@RestController
@RequestMapping("/api")
public class CarController {
    private final CarManagerService carManagerService;

    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(car);
        return new ResponseEntity<>(saved, status);
        //return ResponseEntity.status(status).body(saved);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }
    
    @GetMapping("/cars/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "carId") Long carId) throws ResolutionException {
        return ResponseEntity.of(carManagerService.getCarDetails(carId));
    }
}