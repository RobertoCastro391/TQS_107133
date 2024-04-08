package ua.deti.tqs.HW1BusTicketSelling.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusRouteSearchDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRouteRepository;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusRouteService;

@ExtendWith(MockitoExtension.class)
public class BusRouteServiceTest {

        @Mock(lenient = true)
        BusRouteRepository busRouteRepository;

        @InjectMocks
        BusRouteService busRouteService;

        @Test
        @DisplayName("Test is bus available")
        void testIsBusAvailable() {
                Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
                Bus bus2 = new Bus(2, "33-DD-33", "Mercedes", "Sprinter", 20, "Renex");

                BusRoute busRoute1 = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 3, 1, bus1);
                BusRoute busRoute2 = new BusRoute("3", "Porto", "Braga", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T09:15:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T10:00:00Z")), 60, 6.00, 0, 2, bus2);

                when(busRouteRepository.findByRouteId("1")).thenReturn(busRoute1);
                when(busRouteRepository.findByRouteId("2")).thenReturn(busRoute2);

                assertTrue(busRouteService.isBusAvailable(busRoute1));
                assertFalse(busRouteService.isBusAvailable(busRoute2));

                verify(busRouteRepository, times(0)).findByRouteId(anyString());
        }

        @Test
        @DisplayName("Test Get bus route by Id")
        void testGetBusRouteById() {

                Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
                Bus bus2 = new Bus(2, "22-CC-22", "Mercedes", "Sprinter", 20, "FlixBus");

                BusRoute busRoute1 = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 3, 1, bus1);
                BusRoute busRoute2 = new BusRoute("2", "Lisboa", "Porto", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:09:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 14.00, 4, 2, bus2);

                when(busRouteRepository.findByRouteId("1")).thenReturn(busRoute1);
                when(busRouteRepository.findByRouteId("2")).thenReturn(busRoute2);

                assertEquals(busRoute1, busRouteService.getBusRouteById("1"));
                assertEquals(busRoute2, busRouteService.getBusRouteById("2"));

                verify(busRouteRepository, times(2)).findByRouteId(anyString());
        }

        @Test
        @DisplayName("Test get all bus routes")
        void testGetBusRoutes() {
                Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
                Bus bus2 = new Bus(2, "22-CC-22", "Mercedes", "Sprinter", 20, "FlixBus");

                BusRoute busRoute1 = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 3, 1, bus1);
                BusRoute busRoute2 = new BusRoute("2", "Lisboa", "Porto", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:09:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 14.00, 4, 2, bus2);

                when(busRouteRepository.findAll()).thenReturn(List.of(busRoute1, busRoute2));

                assertEquals(List.of(busRoute1, busRoute2), busRouteService.getBusRoutes());

                verify(busRouteRepository, times(1)).findAll();
        }

        @Test
        @DisplayName("Test search bus route")
        public void testSearchBusRoute() {
                Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");

                BusRoute busRoute1 = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 3, 1, bus1);

                when(busRouteRepository.findByDepartureCityAndArrivalCityAndDepartureDate("Porto", "Lisboa",
                                LocalDate.parse("2024-04-05")))
                                .thenReturn(List.of(busRoute1));

                assertEquals(List.of(busRoute1), busRouteService.searchBusRoute(
                                new BusRouteSearchDTO("Porto", "Lisboa", LocalDate.parse("2024-04-05"))));

                verify(busRouteRepository, times(1)).findByDepartureCityAndArrivalCityAndDepartureDate("Porto",
                                "Lisboa", LocalDate.parse("2024-04-05"));
        }
}
