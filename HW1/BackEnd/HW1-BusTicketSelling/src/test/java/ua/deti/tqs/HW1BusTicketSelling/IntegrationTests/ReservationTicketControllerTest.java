package ua.deti.tqs.HW1BusTicketSelling.IntegrationTests;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusReservationDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Entity.Client;
import ua.deti.tqs.HW1BusTicketSelling.Entity.ReservationTicket;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusRouteService;
import ua.deti.tqs.HW1BusTicketSelling.Service.ReservationTicketService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationTicketControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ReservationTicketService reservationTicketService;

        @MockBean
        private BusRouteService busRouteService;

        private String url = "/api/reservation";

        @BeforeEach
        public void setUp() {

                Bus bus1 = new Bus(1, "11-BB-11", "Mercedes", "Sprinter", 20, "Renex");

                BusRoute busRoute1 = new BusRoute("1", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 20, 1, bus1);

                BusRoute busRoute2 = new BusRoute("2", "Porto", "Lisboa", LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T12:00:00Z")), LocalDate.parse("2024-04-05"),
                                Date.from(Instant.parse("2024-04-05T15:00:00Z")), 350, 10.00, 0, 1, bus1);

                Mockito.when(busRouteService.getBusRouteById("1")).thenReturn(busRoute1);
                Mockito.when(busRouteService.isBusAvailable(busRoute1)).thenReturn(true);
                Mockito.when(busRouteService.isBusAvailable(busRoute2)).thenReturn(false);

                Client client = new Client(1, "Joao", "Silva", "joaosilva@gmail.com", "Rua do Joao", "4000-000",
                                "Porto",
                                "Portugal", "912345678");

                ReservationTicket rt1 = new ReservationTicket("ASWED2", client.getClientId(), client,
                                busRoute1.getRouteId(),
                                busRoute1, 10.00, Date.from(Instant.now()), "11112222", "12/24", "123", "CONFIRMED");

                ReservationTicket mockTicket = new ReservationTicket();
                mockTicket.setTicketId("TICKET123");
                Mockito.when(reservationTicketService.getTicketById("ASWED2")).thenReturn(rt1);
                Mockito.when(reservationTicketService.createTicket(Mockito.any(BusReservationDTO.class)))
                                .thenReturn(mockTicket);
        }

        @Test
        @DisplayName("Testing endpoint - GET /api/reservation/getReservation/{reservationId}")
        void testGetReservationById() throws Exception {
                mockMvc.perform(get(url + "/getReservation/ASWED2")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk()).andExpect(jsonPath("$.ticketId").value("ASWED2"));
        }

        @Test
        @DisplayName("Create Reservation Successfully")
        void createReservationSuccess() throws Exception {
                BusReservationDTO reservationDTO = new BusReservationDTO();
                reservationDTO.setRouteId("1");
                reservationDTO.setClientName("Joao");
                reservationDTO.setClientSurname("Silva");
                reservationDTO.setClientEmail("joaosilva@ua.pt");
                reservationDTO.setClientAddress("Rua do Joao");
                reservationDTO.setClientPostalCode("4000-000");
                reservationDTO.setClientCity("Porto");
                reservationDTO.setClientCountry("Portugal");
                reservationDTO.setClientPhone("912345678");
                reservationDTO.setReservationDate(Date.from(Instant.now()));
                reservationDTO.setCreditCardNumber("11112222");
                reservationDTO.setCreditCardExpiration("12/24");
                reservationDTO.setCreditCardCVV("123");
                reservationDTO.setPrice(10.00);

                ObjectMapper objectMapper = new ObjectMapper();
                mockMvc.perform(post(url + "/createReservation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reservationDTO)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.ticketId").value("TICKET123"));
        }

        @Test
        @DisplayName("Create Reservation Error - Bus is full")
        void createReservationErrorBusIsFull() throws Exception {
                BusReservationDTO reservationDTO = new BusReservationDTO();
                reservationDTO.setRouteId("2");
                reservationDTO.setClientName("Joao");
                reservationDTO.setClientSurname("Silva");
                reservationDTO.setClientEmail("joaosilva@ua.pt");
                reservationDTO.setClientAddress("Rua do Joao");
                reservationDTO.setClientPostalCode("4000-000");
                reservationDTO.setClientCity("Porto");
                reservationDTO.setClientCountry("Portugal");
                reservationDTO.setClientPhone("912345678");
                reservationDTO.setReservationDate(Date.from(Instant.now()));
                reservationDTO.setCreditCardNumber("11112222");
                reservationDTO.setCreditCardExpiration("12/24");
                reservationDTO.setCreditCardCVV("123");
                reservationDTO.setPrice(10.00);

                ObjectMapper objectMapper = new ObjectMapper();
                mockMvc.perform(post(url + "/createReservation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reservationDTO)))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().string("Bus is full"));
        }

        @Test
        @DisplayName("Create Reservation Failure - Ticket Creation Returns Null")
        void createReservationFailureTicketIsNull() throws Exception {

                BusReservationDTO reservationDTO = new BusReservationDTO();
                reservationDTO.setRouteId("1");
                reservationDTO.setClientName("Joao");
                reservationDTO.setClientSurname("Silva");
                reservationDTO.setClientEmail("joaosilva@ua.pt");
                reservationDTO.setClientAddress("Rua do Joao");
                reservationDTO.setClientPostalCode("4000-000");
                reservationDTO.setClientCity("Porto");
                reservationDTO.setClientCountry("Portugal");
                reservationDTO.setClientPhone("912345678");
                reservationDTO.setReservationDate(Date.from(Instant.now()));
                reservationDTO.setCreditCardNumber("11112222");
                reservationDTO.setCreditCardExpiration("12/24");
                reservationDTO.setCreditCardCVV("123");
                reservationDTO.setPrice(10.00);

                Mockito.when(reservationTicketService.createTicket(Mockito.any(BusReservationDTO.class)))
                                .thenReturn(null);

                ObjectMapper objectMapper = new ObjectMapper();

                mockMvc.perform(post(url + "/createReservation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reservationDTO)))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().string("Error creating reservation"));
        }

}