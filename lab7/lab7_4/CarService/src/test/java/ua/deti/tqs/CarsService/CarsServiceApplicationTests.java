package ua.deti.tqs.CarsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import ua.deti.tqs.CarsService.entities.Car;
import ua.deti.tqs.CarsService.repository.CarRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarsServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private CarRepository carRepository;


	@Container
	public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
			.withDatabaseName("test")
			.withUsername("test")
			.withPassword("test");

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@AfterEach
	public void tearDown() {
		carRepository.deleteAll();
	}

	@Test
    public void whenPostCar_thenReturnCar() throws Exception {
        
        Car car = new Car("Ferrari", "F8");

        given()
			.port(port)
            .contentType(ContentType.JSON)
            .body(new ObjectMapper().writeValueAsString(car))
		.when()
			.post("/api/cars")
		.then()
			.statusCode(201)
			.contentType(ContentType.JSON)
			.body("model", equalTo("F8"))
			.body("maker", equalTo("Ferrari"));
			
    }

	@Test
    public void whenGetAllCars_thenReturnCars() throws Exception {
        
        Car car = new Car("BMW", "116D");
		carRepository.save(car);
        Car car2 = new Car("Kia", "Sportage");
		carRepository.save(car2);
        Car car3 = new Car("Ferrari", "F8");
		carRepository.save(car3);

        given()
			.port(port)
		.when()
			.get("/api/cars")
		.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("size()", is(3))
            .body("[0].maker", equalTo("BMW"))
            .body("[0].model", equalTo("116D"))
            .body("[1].maker", equalTo("Kia"))
            .body("[1].model", equalTo("Sportage"))
            .body("[2].maker", equalTo("Ferrari"))
            .body("[2].model", equalTo("F8"));
			
    }

	@Test
    public void whenGetCarById_thenReturnCar() throws Exception {

		Car car = new Car("Ferrari", "F8");
		carRepository.save(car);

		given()
			.port(port)
		.when()
			.get("/api/cars/1")
		.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
            .body("maker", equalTo("Ferrari"))
            .body("model", equalTo("F8"));

	}
}