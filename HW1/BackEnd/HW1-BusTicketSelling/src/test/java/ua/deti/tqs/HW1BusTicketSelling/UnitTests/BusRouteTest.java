package ua.deti.tqs.hw1busticketselling.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.hw1busticketselling.entity.Bus;
import ua.deti.tqs.hw1busticketselling.entity.BusRoute;

class BusRouteTest {

    private BusRoute busRoute;
    private Bus bus;

    @BeforeEach
    void setUp() {
        busRoute = new BusRoute();
    }

    @AfterEach
    void tearDown() {
        busRoute = null;
    }

    @Test
    @DisplayName("Test Set and Get RouteId")
    void testSetGetRouteId() {
        busRoute.setRouteId("1");
        assertEquals("1", busRoute.getRouteId());
    }

    @Test
    @DisplayName("Test Set and Get DepartureCity")
    void testSetGetDepartureCity() {
        busRoute.setDepartureCity("Porto");
        assertEquals("Porto", busRoute.getDepartureCity());
    }

    @Test
    @DisplayName("Test Set and Get ArrivalCity")
    void testSetGetArrivalCity() {
        busRoute.setArrivalCity("Lisboa");
        assertEquals("Lisboa", busRoute.getArrivalCity());
    }

    @Test
    @DisplayName("Test Set and Get Departure Date and Time")
    void testSetGetDepartureDateTime() {

        LocalDate departureTime = LocalDate.of(2024, 11, 10);

        busRoute.setDepartureDate(departureTime);
        assertEquals(busRoute.getDepartureDate(), departureTime);

        Date date = Date.from(java.time.Instant.now());
        busRoute.setDepartureTime(date);
        assertEquals(date, busRoute.getDepartureTime());
    }

    @Test
    @DisplayName("Test Set and Get Arrival Time")
    void testSetGetArrivalDateTime() {
        LocalDate departureTime = LocalDate.of(2024, 11, 10);

        busRoute.setArrivalDate(departureTime);

        System.out.println(busRoute.getArrivalDate());

        assertEquals(busRoute.getArrivalDate(), departureTime);

        Date date = Date.from(java.time.Instant.now());
        busRoute.setArrivalTime(date);
        assertEquals(busRoute.getArrivalTime(), date);
    }

    @Test
    @DisplayName("Test Set and Get Duration")
    void testSetGetDuration() {
        busRoute.setDuration(120);
        assertEquals(120, busRoute.getDuration());
    }

    @Test
    @DisplayName("Test Set and Get Price")
    void testSetGetPrice() {
        busRoute.setPrice(20.0);
        assertEquals(20.0, busRoute.getPrice());
    }

    @Test
    @DisplayName("Test Set and Get SeatsAvailable")
    void testSetGetSeatsAvailable() {
        busRoute.setBusSeatsAvailable(50);
        assertEquals(50, busRoute.getBusSeatsAvailable());
    }

    @Test
    @DisplayName("Test Set and Get BusId and Bus Info")
    void testSetGetBusId() {
        busRoute.setBusId(1);
        bus = new Bus();
        busRoute.setBusInfo(bus);

        assertEquals(1, busRoute.getBusId());
        assertEquals(bus,busRoute.getBusInfo());
    }
}