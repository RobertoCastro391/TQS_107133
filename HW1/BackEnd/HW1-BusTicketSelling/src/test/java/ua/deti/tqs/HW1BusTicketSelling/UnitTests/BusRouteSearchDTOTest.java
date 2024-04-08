package ua.deti.tqs.HW1BusTicketSelling.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusRouteSearchDTO;

public class BusRouteSearchDTOTest {

    private BusRouteSearchDTO busRouteSearchDTO;
    
    @BeforeEach
    void setUp() {
        busRouteSearchDTO = new BusRouteSearchDTO();
    }

    @AfterEach
    void tearDown() {
        busRouteSearchDTO = null;
    }

    @Test
    @DisplayName("Test Set and Get DepartureCity")
    void testSetGetDepartureCity() {
        busRouteSearchDTO.setDepartureCity("Porto");
        assertEquals("Porto", busRouteSearchDTO.getDepartureCity());
    }

    @Test
    @DisplayName("Test Set and Get ArrivalCity")
    void testSetGetArrivalCity() {
        busRouteSearchDTO.setArrivalCity("Lisboa");
        assertEquals("Lisboa", busRouteSearchDTO.getArrivalCity());
    }

    @Test
    @DisplayName("Test Set and Get Date")
    void testSetGetDate() {
        LocalDate date = LocalDate.of(2024, 11, 10);
        busRouteSearchDTO.setDate(date);
        assertEquals(date, busRouteSearchDTO.getDate());
    }
}
