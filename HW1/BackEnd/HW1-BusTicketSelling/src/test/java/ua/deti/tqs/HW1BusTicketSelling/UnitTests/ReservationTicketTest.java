package ua.deti.tqs.hw1busticketselling.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.hw1busticketselling.entity.BusRoute;
import ua.deti.tqs.hw1busticketselling.entity.Client;
import ua.deti.tqs.hw1busticketselling.entity.ReservationTicket;

class ReservationTicketTest {

    private ReservationTicket ticket;
    private BusRoute busRoute;
    private Client client;

    @BeforeEach
    void setUp() {
        ticket = new ReservationTicket();
    }

    @AfterEach
    void tearDown() {
        ticket = null;
    }
    
    @Test 
    @DisplayName("Test Generate, Set and Get TicketId")
    void testGenerateGetSetTicketId() {
        
        ticket.generateReservationId();
        assert(ticket.getTicketId() != null);

        ticket.setTicketId("AS23DF");
        assertEquals("AS23DF", ticket.getTicketId());
    }

    @Test 
    @DisplayName("Test Set and Get ClientId and Client")
    void testGenerateGetSetClientIdClient() {
        
        ticket.setClientId(1);
        assertEquals(1, ticket.getClientId());

        client = new Client();

        ticket.setClient(client);
        assertEquals(client, ticket.getClient());
    }

    @Test 
    @DisplayName("Test Set and Get BusRouteId and BusRoute")
    void testGenerateGetSetBusRouteIdBusRoute() {
        
        ticket.setBusRouteId("1");
        assertEquals("1", ticket.getBusRouteId());

        busRoute = new BusRoute();

        ticket.setBusRouteInfo(busRoute);
        assertEquals(busRoute, ticket.getBusRouteInfo());
    }

    @Test
    @DisplayName("Test Set and Get Price")
    void testSetGetPrice() {
        ticket.setPrice(10.0);
        assertEquals(10.0, ticket.getPrice());
    }

    @Test
    @DisplayName("Test Set Get Reservation Date")
    void testSetGetReservationDate() {
        Date date = Date.valueOf("2024-11-10");
        ticket.setReservationDate(date);
        assertEquals(date, ticket.getReservationDate());
    }

    @Test
    @DisplayName("Test Set Get Credit Card Details")
    void testSetGetCreditCardDetails() {
        ticket.setCreditCardNumber("1234567890123456");
        ticket.setCreditCardExpiration("12/2024");
        ticket.setCreditCardCVV("123");

        assertEquals("1234567890123456", ticket.getCreditCardNumber());
        assertEquals("12/2024", ticket.getCreditCardExpiration());
        assertEquals("123", ticket.getCreditCardCVV());
    }

    @Test
    @DisplayName("Test Set Get Reservation Status")
    void testSetGetReservationStatus() {
        ticket.setReverStatus("Confirmed");
        assertEquals("Confirmed", ticket.getReverStatus());
    }
}