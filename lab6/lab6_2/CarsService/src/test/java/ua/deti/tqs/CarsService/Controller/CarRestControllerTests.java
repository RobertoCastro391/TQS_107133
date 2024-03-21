package ua.deti.tqs.CarsService.Controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import ua.deti.tqs.CarsService.Car;
import ua.deti.tqs.CarsService.Controllers.CarController;
import ua.deti.tqs.CarsService.Services.CarManagerService;

@WebMvcTest(CarController.class)
public class CarRestControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void whenPostCar_thenReturnCar() throws Exception {
        Car car = new Car("Ferrari", "F8");

        when(carManagerService.save(car)).thenReturn(car);
        String carJson = new ObjectMapper().writeValueAsString(car);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(carJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Ferrari")))
                .andExpect(jsonPath("$.model", is("F8"))); // Corrected JSON path

        verify(carManagerService, times(1)).save(car);
    }

    
    @Test
    public void whenGetAllCars_thenReturnCars() throws Exception {
        Car car = new Car("BMW", "116D");
        Car car2 = new Car("Kia", "Sportage");
        Car car3 = new Car("Ferrari", "F8");

        when(carManagerService.getAllCars()).thenReturn(List.of(car, car2, car3));

        mvc.perform(
            get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].maker", is("BMW")))
            .andExpect(jsonPath("$[0].model", is("116D")))
            .andExpect(jsonPath("$[1].maker", is("Kia")))
            .andExpect(jsonPath("$[1].model", is("Sportage")))
            .andExpect(jsonPath("$[2].maker", is("Ferrari")))
            .andExpect(jsonPath("$[2].model", is("F8")));
       
        
        verify(carManagerService, times(1)).getAllCars();
    }

    @Test
    public void whenGetCarById_thenReturnCar() throws Exception {
        Car car = new Car("Ferrari", "F8");

        when(carManagerService.getCarDetails(1L)).thenReturn(java.util.Optional.of(car));

        mvc.perform(
            get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is("Ferrari")));
        
        verify(carManagerService, times(1)).getCarDetails(1L);
    }               
}