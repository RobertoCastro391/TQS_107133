package ua.deti.tqs.CarsService.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ua.deti.tqs.CarsService.Car;
import ua.deti.tqs.CarsService.Repository.CarRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarRestControllerTemplateIT {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private CarRepository carRepository;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void whenPostCar_thenReturnCar() {
        Car car = new Car("BMW", "116D");
        car.setCarId(1L);
        restTemplate.postForEntity("/api/cars", car, Car.class);

        Car carResponse = carRepository.findById(1L).get();

        assertThat(carResponse).isEqualTo(car);
    }

    @Test
    public void whenGetAllCars_thenReturnCars() {
        Car car = new Car("BMW", "116D");
        Car car2 = new Car("Kia", "Sportage");
        carRepository.save(car);
        carRepository.save(car2);

        ResponseEntity<List<Car>> response = restTemplate
            .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Car> cars = response.getBody();
        assertThat(cars).contains(car, car2);
    }

    @Test
    public void whenGetCarById_thenReturnCar() {
        Car car = new Car("BMW", "116D");
        carRepository.save(car);

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/cars/1", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(car);
    }

    @Test
    public void whenInvalidId_thenReturnNotFound() {
        ResponseEntity<Car> response = restTemplate
            .getForEntity("/api/car/1", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}