package ua.deti.tqs.hw1busticketselling.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.hw1busticketselling.entity.Client;

class ClientTest {

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
        assertEquals(1, client.getClientId());
    }

    @Test
    @DisplayName("Test Set and Get ClientName")
    void testSetGetClientName() {
        client.setClientName("Roberto");
        assertEquals("Roberto", client.getClientName());
    }

    @Test
    @DisplayName("Test Set and Get ClientSurname")
    void testSetGetClientSurname() {
        client.setClientSurname("Castro");
        assertEquals("Castro", client.getClientSurname());
    }

    @Test
    @DisplayName("Test Set and Get ClientEmail")
    void testSetGetClientEmail() {
        client.setClientEmail("robertorcastro@ua.pt");
        assertEquals("robertorcastro@ua.pt", client.getClientEmail());
    }

    @Test
    @DisplayName("Test Set and Get ClientAddress")
    void testSetGetClientAddress() {
        client.setClientAddress("Rua do Campo Alegre");
        assertEquals("Rua do Campo Alegre", client.getClientAddress());
    }

    @Test
    @DisplayName("Test Set and Get ClientPostalCode")
    void testSetGetClientPostalCode() {
        client.setClientPostalCode("4200-465");
        assertEquals("4200-465", client.getClientPostalCode());

    }

    @Test
    @DisplayName("Test Set and Get ClientCity")
    void testSetGetClientCity() {
        client.setClientCity("Porto");
        assertEquals("Porto", client.getClientCity());

    }

    @Test
    @DisplayName("Test Set and Get ClientCountry")
    void testSetGetClientCountry() {
        client.setClientCountry("Portugal");
        assertEquals("Portugal", client.getClientCountry());
    }

    @Test
    @DisplayName("Test Set and Get ClientPhone")
    void testSetGetClientPhone() {
        client.setClientPhone("912345678");
        assertEquals("912345678", client.getClientPhone());
    }
}