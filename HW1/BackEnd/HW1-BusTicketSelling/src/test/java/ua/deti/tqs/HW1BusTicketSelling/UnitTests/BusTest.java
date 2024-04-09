package ua.deti.tqs.hw1busticketselling.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.hw1busticketselling.entity.Bus;

class BusTest {

    private Bus bus;

    @BeforeEach
    void setUp() {
        bus = new Bus();
    }

    @AfterEach
    void tearDown() {
        bus = null;
    }

    @Test
    @DisplayName("Test Set and Get Bus ID")
    void testSetGetBusId() {

        bus.setBusId(2);
        assertEquals(2, bus.getBusId());
    }

    @Test
    @DisplayName("Test Set and Get Bus License Plate")
    void testSetGetBusLicensePlate() {

        bus.setBusLicensePlate("AA-BB-01");
        assertEquals("AA-BB-01", bus.getBusLicensePlate());
    }

    @Test
    @DisplayName("Test Set and Get Bus Brand")
    void testSetBusBrand() {

        bus.setBusBrand("Volvo");
        assertEquals("Volvo", bus.getBusBrand());
    }

    @Test
    @DisplayName("Test Set and Get Bus Model")
    void testSetGetBusModel() {

        bus.setBusModel("V60");
        assertEquals("V60", bus.getBusModel());
    }

    @Test
    @DisplayName("Test Set and Get Bus Capacity")
    void testSetGetBusCapacity() {

        bus.setBusSeats(30);
        assertEquals(30,bus.getBusSeats());
    }

    @Test
    @DisplayName("Test Set and Get Bus Company")
    void testSetGetBusCompany() {

        bus.setBusCompany("Flexibus");
        assertEquals("Flexibus", bus.getBusCompany());
    }   
}