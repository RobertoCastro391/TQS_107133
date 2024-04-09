package ua.deti.tqs.hw1busticketselling.integrationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ua.deti.tqs.hw1busticketselling.dto.BusRouteSearchDTO;
import ua.deti.tqs.hw1busticketselling.entity.Bus;
import ua.deti.tqs.hw1busticketselling.entity.BusRoute;
import ua.deti.tqs.hw1busticketselling.service.BusRouteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
class BusRouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusRouteService busRouteService;

    private String url = "/api/busRoute";

    @BeforeEach
    void setUp() {
        Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
        Bus bus2 = new Bus(2, "33-DD-33", "Mercedes", "Sprinter", 20, "Renex");

        BusRoute busRoute1 = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 3, 1, bus1);
        BusRoute busRoute2 = new BusRoute("2", "Porto", "Braga", LocalDate.parse("2024-04-05"),
                Date.from(Instant.parse("2024-04-05T09:15:00Z")), LocalDate.parse("2024-04-05"),
                Date.from(Instant.parse("2024-04-05T10:00:00Z")), 60, 6.00, 0, 2, bus2);

        Mockito.when(busRouteService.getBusRouteById("1")).thenReturn(busRoute1);
        Mockito.when(busRouteService.getBusRoutes())
                .thenReturn(java.util.List.of(busRoute1, busRoute2));
        Mockito.when(busRouteService.searchBusRoute(Mockito.any(BusRouteSearchDTO.class)))
                .thenReturn(java.util.List.of(busRoute1));

        Mockito.when(busRouteService.searchBusRoute(Mockito.argThat(
                dto -> "Porto".equals(dto.getDepartureCity()) &&
                        "Lisboa".equals(dto.getArrivalCity()) &&
                        "2024-04-06".equals(dto.getDate().toString()))))
                .thenReturn(java.util.Collections.emptyList());
    }

    @Test
    @DisplayName("Testing endpoint - GET /api/busRoute/getBusRoute/{busRouteId}")
    void testGetBusById() throws Exception {
        mockMvc.perform(get(url + "/getBusRoute/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value("1"));
    }

    @Test
    @DisplayName("Testing endpoint - GET /api/busRoute/getBusRoutes")
    void testGetBuses() throws Exception {
        mockMvc.perform(get(url + "/getBusRoutes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Testing endpoint - POST /api/busRoute/searchBusRoute")
    void testSearchBusRoute() throws Exception {
        String searchCriteriaJson = """
                {
                    "departureCity": "Porto",
                    "arrivalCity": "Lisboa",
                    "date": "2024-04-05"
                }
                """;

        mockMvc.perform(post(url + "/searchBusRoute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchCriteriaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.busRoutes", hasSize(1)))
                .andExpect(jsonPath("$.busRoutes[0].routeId").value("1"));

    }

    @Test
    @DisplayName("Testing endpoint - POST /api/busRoute/searchBusRoute with no results")
    void testSearchBusRouteWithNoResults() throws Exception {
        String searchCriteriaJson = """
                {
                    "departureCity": "Porto",
                    "arrivalCity": "Lisboa",
                    "date": "2024-04-06"
                }
                """;

        mockMvc.perform(post(url + "/searchBusRoute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchCriteriaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("No bus routes found"));
    }

}