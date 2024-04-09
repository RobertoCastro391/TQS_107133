package ua.deti.tqs.CarsService.Repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ua.deti.tqs.CarsService.Car;

@DataJpaTest
public class CarRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testFindByCarId() {
        Car car1 = new Car("BMW", "X6");
        entityManager.persistAndFlush(car1);
        assertTrue(carRepository.findByCarId(car1.getCarId()).equals(car1));
        assertNotNull(car1);
    }

    @Test
    public void testIdNotValid() {
        assertTrue(carRepository.findByCarId(1L) == null);
    }

    @Test
    public void testFindAll() {
        Car car1 = new Car("BMW", "X6");
        Car car2 = new Car("FIAT", "500");
        Car car3 = new Car("KIA", "Sorento");
        Car car4 = new Car("VOLVO", "XC40");
        Car car5 = new Car("SEAT", "Ibiza");

        entityManager.persistAndFlush(car1);
        entityManager.persistAndFlush(car2);
        entityManager.persistAndFlush(car3);
        entityManager.persistAndFlush(car4);
        entityManager.persistAndFlush(car5);

        assertTrue(carRepository.findAll().size() == 5);
    }
}