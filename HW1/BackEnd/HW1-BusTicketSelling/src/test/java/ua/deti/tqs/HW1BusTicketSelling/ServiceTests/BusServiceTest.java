package ua.deti.tqs.HW1BusTicketSelling.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRepository;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusService;

@ExtendWith(MockitoExtension.class)
public class BusServiceTest {


    @Mock(lenient = true)
    BusRepository busRepository;
    
    @InjectMocks
    BusService busService;

    @Test
    @DisplayName("Test Get bus by Id")
    void testGetBusById() {

        Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
        Bus bus2 = new Bus(2, "22-CC-22", "Mercedes", "Sprinter", 20, "FlixBus");
        Bus bus3 = new Bus(3, "33-DD-33", "Mercedes", "Sprinter", 20, "Renex");
        
    
        when(busRepository.findById(1)).thenReturn(Optional.of(bus1));
        when(busRepository.findById(2)).thenReturn(Optional.of(bus2));
        when(busRepository.findById(3)).thenReturn(Optional.of(bus3));

        assertEquals(bus1, busService.getBusById(1));
        assertEquals(bus2, busService.getBusById(2));
        assertEquals(bus3, busService.getBusById(3));

        verify(busRepository, times(3)).findById(anyInt());
    }

    @Test
    @DisplayName("Test Get all buses")
    void testGetAllBus() {

        Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
        Bus bus2 = new Bus(2, "22-CC-22", "Mercedes", "Sprinter", 20, "FlixBus");
        Bus bus3 = new Bus(3, "33-DD-33", "Mercedes", "Sprinter", 20, "Renex");

        when(busRepository.findAll()).thenReturn(List.of(bus1, bus2, bus3));
        
        assertEquals(List.of(bus1, bus2, bus3), busService.getBuses());

        verify(busRepository, times(1)).findAll();
    }
}