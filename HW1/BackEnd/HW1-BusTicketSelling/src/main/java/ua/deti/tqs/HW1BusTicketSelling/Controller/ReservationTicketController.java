package ua.deti.tqs.HW1BusTicketSelling.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.*;

import lombok.AllArgsConstructor;
import ua.deti.tqs.HW1BusTicketSelling.DTO.BusReservationDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.ReservationTicket;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusRouteService;
import ua.deti.tqs.HW1BusTicketSelling.Service.ClientService;
import ua.deti.tqs.HW1BusTicketSelling.Service.ReservationTicketService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationTicketController {

    private final BusRouteService busRouteService;
    private final ReservationTicketService ticketReservationService;
    private final ClientService clientService;

    private static Logger log = LogManager.getLogger(BusRouteController.class);


    @GetMapping("/getReservation/{reservationId}")
    public ResponseEntity<ReservationTicket> getTicketReservatinByTicketId(
            @PathVariable("reservationId") String reservationId) {

        log.info("GET Reservation by reservationId: " + reservationId);

        return ResponseEntity.ok(ticketReservationService.getTicketById(reservationId));
    }

    @GetMapping("/getReservationsByClient/{clientName}")
    public ResponseEntity<List<ReservationTicket>> getTicketReservationsByClientId(
            @PathVariable("clientName") String clientName) {

        log.info("GET Reservations by clientName: " + clientName);
        String[] name = clientName.split(" ");
        int clientId = clientService.getClientIdByNameAndSurname(name[0], name[1]);
        List<ReservationTicket> tickets = ticketReservationService.getTicketsByClient(clientId);
        log.info("Response: " + tickets);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/createReservation")
    public ResponseEntity<?> createReservation(@RequestBody BusReservationDTO reservationDTO) {

        log.info("POST Create Reservation: " + reservationDTO.toString());

        if (!busRouteService.isBusAvailable(busRouteService.getBusRouteById(reservationDTO.getRouteId()))) {
            log.info("Error: Bus is full");
            return ResponseEntity.badRequest().body("Bus is full");
        }

        ReservationTicket ticket = ticketReservationService.createTicket(reservationDTO);

        if (ticket == null) {
            log.info("Error: Error creating reservation");
            return ResponseEntity.badRequest().body("Error creating reservation");
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("ticketId", ticket.getTicketId());
            log.info("Response: " + response);
            return ResponseEntity.ok().body(response);
        }
    }
}