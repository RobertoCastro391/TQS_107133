package ua.deti.tqs.HW1BusTicketSelling.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;

public class BusTest {

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
        assertEquals(bus.getBusId(), 2);
    }

    @Test
    @DisplayName("Test Set and Get Bus License Plate")
    void testSetGetBusLicensePlate() {

        bus.setBusLicensePlate("AA-BB-01");
        assertEquals(bus.getBusLicensePlate(), "AA-BB-01");
    }

    @Test
    @DisplayName("Test Set and Get Bus Brand")
    void testSetBusBrand() {

        bus.setBusBrand("Volvo");
        assertEquals(bus.getBusBrand(), "Volvo");
    }

    @Test
    @DisplayName("Test Set and Get Bus Model")
    void testSetGetBusModel() {

        bus.setBusModel("V60");
        assertEquals(bus.getBusModel(), "V60");
    }

    @Test
    @DisplayName("Test Set and Get Bus Capacity")
    void testSetGetBusCapacity() {

        bus.setBusSeats(30);
        assertEquals(bus.getBusSeats(), 30);
    }

    @Test
    @DisplayName("Test Set and Get Bus Company")
    void testSetGetBusCompany() {

        bus.setBusCompany("Flexibus");
        assertEquals(bus.getBusCompany(),"Flexibus");
    }   
}