package ua.deti.tqs.CarsService.Service;



import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.deti.tqs.CarsService.Car;
import ua.deti.tqs.CarsService.Repository.CarRepository;
import ua.deti.tqs.CarsService.Services.CarManagerService;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void setUp1() {
        carRepository.deleteAll();
    }

    @Test
    public void testSaveCar() {
        Car car1 = new Car("BMW", "X6");
        Mockito.when(carRepository.save(car1)).thenReturn(car1);

        assertTrue(carManagerService.save(car1).equals(car1));
        verify(carRepository, Mockito.times(1)).save(car1);
    }

    @Test
    public void testGetAllCars() {
        Car car1 = new Car("BMW", "X6");
        Car car2 = new Car("FIAT", "500");
        Car car3 = new Car("KIA", "Sorento");
        Car car4 = new Car("VOLVO", "XC40");
        Car car5 = new Car("SEAT", "Ibiza");

        Mockito.when(carRepository.findAll()).thenReturn(java.util.Arrays.asList(car1, car2, car3, car4, car5));
        assertTrue(carManagerService.getAllCars().size() == 5);
        verify(carRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetCarDetails() {
        Car car1 = new Car("BMW", "X6");
        Mockito.when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car1));
        assertNotEquals(carManagerService.getCarDetails(1L), car1);
        verify(carRepository, Mockito.times(1)).findById(1L);
    }
}
