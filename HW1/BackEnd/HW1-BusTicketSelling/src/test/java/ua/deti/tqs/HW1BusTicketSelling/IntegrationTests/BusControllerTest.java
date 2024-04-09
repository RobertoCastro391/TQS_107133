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

import ua.deti.tqs.hw1busticketselling.entity.Bus;
import ua.deti.tqs.hw1busticketselling.service.BusService;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusService busService;

    String url = "/api/bus";

    @BeforeEach
    void setUp() {
        Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
        Bus bus2 = new Bus(2, "33-DD-33", "Mercedes", "Sprinter", 20, "Renex");
        
        Mockito.when(busService.getBusById(1)).thenReturn(bus1);
        Mockito.when(busService.getBuses()).thenReturn(java.util.List.of(bus1, bus2));
    }

    @Test
    @DisplayName("Testing endpoint - GET /api/bus/getBus/{busId}")
    void testGetBusById() throws Exception {
        mockMvc.perform(get(url + "/getBus/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.busId").value(1));
    }

    @Test
    @DisplayName("Testing endpoint - GET /api/bus/getBuses")
    void testGetBuses() throws Exception {
        mockMvc.perform(get(url + "/getBuses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}

