package ua.deti.tqs.hw1busticketselling.serviceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ua.deti.tqs.hw1busticketselling.config.CSVToDB;
import ua.deti.tqs.hw1busticketselling.entity.Bus;
import ua.deti.tqs.hw1busticketselling.entity.BusRoute;
import ua.deti.tqs.hw1busticketselling.repository.BusRepository;
import ua.deti.tqs.hw1busticketselling.repository.BusRouteRepository;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class CSVToDBTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private BusRouteRepository busRouteRepository;

    @InjectMocks
    private CSVToDB csvToDB;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        csvToDB.setCSVFiles("src/test/resources/test_buses.csv", "src/test/resources/test_bus_routes.csv");
    }

    @Test
    @DisplayName("Test loadBuses")
    void testLoadBuses() throws Exception {
        when(busRepository.findByBusLicensePlate(anyString())).thenReturn(null);

        csvToDB.loadBuses();

        verify(busRepository, times(2)).save(any(Bus.class));
    }

    @Test
    @DisplayName("Test loadBusRoutes")
    void testLoadBusRoutes() throws Exception {
        when(busRouteRepository.findByRouteId(anyString())).thenReturn(null);
        when(busRepository.findByBusLicensePlate(anyString())).thenReturn(new Bus());

        csvToDB.loadBusRoutes();

        verify(busRouteRepository, times(2)).save(any(BusRoute.class));
    }
}
