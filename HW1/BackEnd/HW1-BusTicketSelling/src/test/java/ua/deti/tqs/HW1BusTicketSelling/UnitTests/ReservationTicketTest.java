package ua.deti.tqs.HW1BusTicketSelling.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Entity.Client;
import ua.deti.tqs.HW1BusTicketSelling.Entity.ReservationTicket;

public class ReservationTicketTest {

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
        assertEquals(ticket.getTicketId(), "AS23DF");
    }

    @Test 
    @DisplayName("Test Set and Get ClientId and Client")
    void testGenerateGetSetClientIdClient() {
        
        ticket.setClientId(1);
        assertEquals(ticket.getClientId(), 1);

        client = new Client();

        ticket.setClient(client);
        assertEquals(ticket.getClient(), client);
    }

    @Test 
    @DisplayName("Test Set and Get BusRouteId and BusRoute")
    void testGenerateGetSetBusRouteIdBusRoute() {
        
        ticket.setBusRouteId("1");
        assertEquals(ticket.getBusRouteId(), "1");

        busRoute = new BusRoute();

        ticket.setBusRouteInfo(busRoute);
        assertEquals(ticket.getBusRouteInfo(), busRoute);
    }

    @Test
    @DisplayName("Test Set and Get Price")
    void testSetGetPrice() {
        ticket.setPrice(10.0);
        assertEquals(ticket.getPrice(), 10.0);
    }

    @Test
    @DisplayName("Test Set Get Reservation Date")
    void testSetGetReservationDate() {
        Date date = Date.valueOf("2024-11-10");
        ticket.setReservationDate(date);
        assertEquals(ticket.getReservationDate(),date);
    }

    @Test
    @DisplayName("Test Set Get Credit Card Details")
    void testSetGetCreditCardDetails() {
        ticket.setCreditCardNumber("1234567890123456");
        ticket.setCreditCardExpiration("12/2024");
        ticket.setCreditCardCVV("123");

        assertEquals(ticket.getCreditCardNumber(), "1234567890123456");
        assertEquals(ticket.getCreditCardExpiration(), "12/2024");
        assertEquals(ticket.getCreditCardCVV(), "123");
    }

    @Test
    @DisplayName("Test Set Get Reservation Status")
    void testSetGetReservationStatus() {
        ticket.setReverStatus("Confirmed");
        assertEquals(ticket.getReverStatus(), "Confirmed");
    }
}