package ua.deti.tqs.HW1BusTicketSelling.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusReservationDTO;

public class BusReservationDTOTest {

    private BusReservationDTO busReservationDTO;

    @BeforeEach
    void setUp() {
        busReservationDTO = new BusReservationDTO();
    }

    @AfterEach
    void tearDown() {
        busReservationDTO = null;
    }

    @Test
    @DisplayName("Test Set and Get RouteId")
    void testSetGetRouteId() {
        busReservationDTO.setRouteId("1");
        assertEquals("1", busReservationDTO.getRouteId());
    }

    @Test
    @DisplayName("Test Set and Get ClientName")
    void testSetGetClientName() {
        busReservationDTO.setClientName("John");
        assertEquals("John", busReservationDTO.getClientName());
    }

    @Test
    @DisplayName("Test Set and Get ClientSurname")
    void testSetGetClientSurname() {
        busReservationDTO.setClientSurname("Doe");
        assertEquals("Doe", busReservationDTO.getClientSurname());
    }

    @Test
    @DisplayName("Test Set and Get ClientEmail")
    void testSetGetClientEmail() {
        busReservationDTO.setClientEmail("johnDoe@ua.pt");
        assertEquals("johnDoe@ua.pt", busReservationDTO.getClientEmail());
    }

    @Test
    @DisplayName("Test Set and Get ClientAddress")
    void testSetGetClientAddress() {
        busReservationDTO.setClientAddress("Rua do ISEP");
        assertEquals("Rua do ISEP", busReservationDTO.getClientAddress());
    }

    @Test
    @DisplayName("Test Set and Get ClientPostalCode")
    void testSetGetClientPostalCode() {
        busReservationDTO.setClientPostalCode("4200-072");
        assertEquals("4200-072", busReservationDTO.getClientPostalCode());
    }

    @Test
    @DisplayName("Test Set and Get ClientCity")
    void testSetGetClientCity() {
        busReservationDTO.setClientCity("Porto");
        assertEquals("Porto", busReservationDTO.getClientCity());
    }

    @Test
    @DisplayName("Test Set and Get ClientCountry")
    void testSetGetClientCountry() {
        busReservationDTO.setClientCountry("Portugal");
        assertEquals("Portugal", busReservationDTO.getClientCountry());
    }

    @Test
    @DisplayName("Test Set and Get ClientPhone")
    void testSetGetClientPhone() {
        busReservationDTO.setClientPhone("912345678");
        assertEquals("912345678", busReservationDTO.getClientPhone());
    }

    @Test
    @DisplayName("Test Set and Get ReservationDate")
    void testSetGetReservationDate() {
        Date date = Date.from(Instant.parse("2024-11-10T00:00:00.00Z"));
        busReservationDTO.setReservationDate(date);
        assertEquals(date, busReservationDTO.getReservationDate());
    }

    @Test
    @DisplayName("Test Set and Get CreditCardNumber")
    void testSetGetCreditCardNumber() {
        busReservationDTO.setCreditCardNumber("1234567890123456");
        assertEquals("1234567890123456", busReservationDTO.getCreditCardNumber());
    }

    @Test
    @DisplayName("Test Set and Get CreditCardExpiration")
    void testSetGetCreditCardExpiration() {
        busReservationDTO.setCreditCardExpiration("11/24");
        assertEquals("11/24", busReservationDTO.getCreditCardExpiration());
    }

    @Test
    @DisplayName("Test Set and Get CreditCardCVV")
    void testSetGetCreditCardCVV() {
        busReservationDTO.setCreditCardCVV("123");
        assertEquals("123", busReservationDTO.getCreditCardCVV());
    }

    @Test
    @DisplayName("Test Set and Get Price")
    void testSetGetPrice() {
        busReservationDTO.setPrice(10.0);
        assertEquals(10.0, busReservationDTO.getPrice());
    }
}