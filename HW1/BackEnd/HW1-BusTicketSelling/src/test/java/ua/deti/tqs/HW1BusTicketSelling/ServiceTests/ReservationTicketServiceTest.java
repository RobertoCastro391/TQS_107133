package ua.deti.tqs.hw1busticketselling.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.deti.tqs.hw1busticketselling.dto.BusReservationDTO;
import ua.deti.tqs.hw1busticketselling.entity.Bus;
import ua.deti.tqs.hw1busticketselling.entity.BusRoute;
import ua.deti.tqs.hw1busticketselling.entity.Client;
import ua.deti.tqs.hw1busticketselling.entity.ReservationTicket;
import ua.deti.tqs.hw1busticketselling.repository.BusRouteRepository;
import ua.deti.tqs.hw1busticketselling.repository.ClientRepository;
import ua.deti.tqs.hw1busticketselling.repository.ReservationTicketRepository;
import ua.deti.tqs.hw1busticketselling.service.ReservationTicketService;

@ExtendWith(MockitoExtension.class)
class ReservationTicketServiceTest {

    @Mock(lenient = true)
    ReservationTicketRepository reservationTicketRepository;

    @Mock(lenient = true)
    ClientRepository clientRepository;

    @Mock(lenient = true)
    BusRouteRepository busRouteRepository;

    @InjectMocks
    ReservationTicketService reservationTicketService;

    @Test
    @DisplayName("Create a ticket successfully")
    void testCreateTicketSuccess() {

        BusReservationDTO dto = new BusReservationDTO("1", "Joao", "Silva", "joaosilva@gmail.com", "Rua do Joao", "4000-000", "Porto", "Portugal", "912345678",  Date.from(Instant.now()), "1234567891234567", "04/24", "123", 10.00);
        Client client = new Client(1, "Joao", "Silva", "joaosilva@gmail.com", "Rua do Joao", "4000-000", "Porto", "Portugal", "912345678");
        Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");
        BusRoute busRoute = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
            Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
            Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 3, 1, bus1);
        
            ReservationTicket expectedReservation = new ReservationTicket("ASDC23", 1, client, "1", busRoute, 10.00, Date.from(Instant.now()), "1234567891234567", "04/24", "123", "CONFIRMED");


        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(busRouteRepository.findByRouteId(anyString())).thenReturn(busRoute);
        when(reservationTicketRepository.save(any(ReservationTicket.class))).thenReturn(expectedReservation);
 
        assertEquals(expectedReservation, reservationTicketService.createTicket(dto));
        verify(reservationTicketRepository, times(1)).save(any(ReservationTicket.class));
    }


    @Test
    @DisplayName("Test Get Reservation Ticket by id")
    void testGetReservationTicketById() {

        Client client = new Client(1, "Joao", "Silva", "joaosilva@gmail.com", "Rua do Joao", "4000-000", "Porto", "Portugal", "912345678");

        Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");

        BusRoute busRoute1 = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 3, 1, bus1);

        ReservationTicket reservationTicket = new ReservationTicket("ASDC23", 1, client, "1", busRoute1, 10.00, Date.from(Instant.now()), "1234567891234567", "04/24", "123", "CONFIRMED");

        when(reservationTicketRepository.findByTicketId("ASDC23")).thenReturn(reservationTicket);
        
        assertEquals(reservationTicket, reservationTicketService.getTicketById("ASDC23"));

        verify(reservationTicketRepository, times(1)).findByTicketId("ASDC23");
    }
}
