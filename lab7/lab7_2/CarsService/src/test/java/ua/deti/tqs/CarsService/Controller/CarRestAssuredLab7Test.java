package ua.deti.tqs.CarsService.Controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.deti.tqs.CarsService.Car;
import ua.deti.tqs.CarsService.Controllers.CarController;
import ua.deti.tqs.CarsService.Services.CarManagerService;

@WebMvcTest(CarController.class)
public class CarRestAssuredLab7Test {

    @MockBean
    CarManagerService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void whenPostCar_thenReturnCar() throws Exception {
        
        Car car = new Car("Ferrari", "F8");

        when(service.save(car)).thenReturn(car);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ObjectMapper().writeValueAsString(car))
                .when()
                .post("/api/cars")
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("maker", equalTo("Ferrari"))
                .body("model", equalTo("F8"));

        verify(service, times(1)).save(car);
    }


    @Test
    public void whenGetAllCars_thenReturnCars() throws Exception {
        Car car = new Car("BMW", "116D");
        Car car2 = new Car("Kia", "Sportage");
        Car car3 = new Car("Ferrari", "F8");

        when(service.getAllCars()).thenReturn(java.util.List.of(car, car2, car3));

        RestAssuredMockMvc.given()
                .when()
                .get("/api/cars")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(3))
                .body("[0].maker", equalTo("BMW"))
                .body("[0].model", equalTo("116D"))
                .body("[1].maker", equalTo("Kia"))
                .body("[1].model", equalTo("Sportage"))
                .body("[2].maker", equalTo("Ferrari"))
                .body("[2].model", equalTo("F8"));

        verify(service, times(1)).getAllCars();
    }
    
    @Test
    public void whenGetCarById_thenReturnCar() throws Exception {

        Car car = new Car("Ferrari", "F8");

        when(service.getCarDetails(1L)).thenReturn(java.util.Optional.of(car));

        RestAssuredMockMvc.given()
                .when()
                .get("/api/cars/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("maker", equalTo("Ferrari"));
    }
}