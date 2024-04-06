package ua.deti.tqs.HW1BusTicketSelling.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Client;

public class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
    }

    @AfterEach
    void tearDown() {
        client = null;
    }

    @Test
    @DisplayName("Test Set and Get ClientId")
    void testSetGetClientId() {
        client.setClientId(1);
        assertEquals(client.getClientId(), 1);
    }

    @Test
    @DisplayName("Test Set and Get ClientName")
    void testSetGetClientName() {
        client.setClientName("Roberto");
        assertEquals(client.getClientName(), "Roberto");
    }

    @Test
    @DisplayName("Test Set and Get ClientSurname")
    void testSetGetClientSurname() {
        client.setClientSurname("Castro");
        assertEquals(client.getClientSurname(), "Castro");
    }

    @Test
    @DisplayName("Test Set and Get ClientEmail")
    void testSetGetClientEmail() {
        client.setClientEmail("robertorcastro@ua.pt");
        assertEquals(client.getClientEmail(), "robertorcastro@ua.pt");
    }

    @Test
    @DisplayName("Test Set and Get ClientAddress")
    void testSetGetClientAddress() {
        client.setClientAddress("Rua do Campo Alegre");
        assertEquals(client.getClientAddress(), "Rua do Campo Alegre");
    }

    @Test
    @DisplayName("Test Set and Get ClientPostalCode")
    void testSetGetClientPostalCode() {
        client.setClientPostalCode("4200-465");
        assertEquals(client.getClientPostalCode(), "4200-465");

    }

    @Test
    @DisplayName("Test Set and Get ClientCity")
    void testSetGetClientCity() {
        client.setClientCity("Porto");
        assertEquals(client.getClientCity(), "Porto");

    }

    @Test
    @DisplayName("Test Set and Get ClientCountry")
    void testSetGetClientCountry() {
        client.setClientCountry("Portugal");
        assertEquals(client.getClientCountry(), "Portugal");
    }

    @Test
    @DisplayName("Test Set and Get ClientPhone")
    void testSetGetClientPhone() {
        client.setClientPhone("912345678");
        assertEquals(client.getClientPhone(), "912345678");
    }
}