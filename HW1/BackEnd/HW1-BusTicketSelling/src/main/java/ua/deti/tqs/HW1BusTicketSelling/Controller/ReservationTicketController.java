package ua.deti.tqs.hw1busticketselling.controller;

import java.util.HashMap;
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
import ua.deti.tqs.hw1busticketselling.dto.BusReservationDTO;
import ua.deti.tqs.hw1busticketselling.entity.ReservationTicket;
import ua.deti.tqs.hw1busticketselling.service.BusRouteService;
import ua.deti.tqs.hw1busticketselling.service.ReservationTicketService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationTicketController {

    private final BusRouteService busRouteService;
    private final ReservationTicketService ticketReservationService;

    private static Logger log = LogManager.getLogger(ReservationTicketController.class);


    @GetMapping("/getReservation/{reservationId}")
    public ResponseEntity<ReservationTicket> getTicketReservatinByTicketId(
            @PathVariable("reservationId") String reservationId) {

        log.info("GET Reservation by reservationId: {}", reservationId);

        return ResponseEntity.ok(ticketReservationService.getTicketById(reservationId));
    }

    @PostMapping("/createReservation")
    public ResponseEntity<Object> createReservation(@RequestBody BusReservationDTO reservationDTO) {

        log.info("Reservation details: {}", reservationDTO);

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
            log.info("Response: {}", response);
            return ResponseEntity.ok().body(response);
        }
    }
}