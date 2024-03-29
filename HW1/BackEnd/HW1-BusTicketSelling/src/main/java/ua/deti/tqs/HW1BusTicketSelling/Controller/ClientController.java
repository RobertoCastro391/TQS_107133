package ua.deti.tqs.HW1BusTicketSelling.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusService;
import ua.deti.tqs.HW1BusTicketSelling.Service.ClientService;
import ua.deti.tqs.HW1BusTicketSelling.Service.ReservationTicketService;

@RestController
@AllArgsConstructor 
@RequestMapping("/api/client")
public class ClientController {

    // private final BusService busService;
    // private final TicketReservationService ticketReservationService;
    // private final ClientService clientService;
    // private final TicketReservationService ticketService;

    // @GetMapping("/getClientTickets/{client_id}")
    // public String getClientTickets(@PathVariable("client_id") int client_id) {
    //     return clientService.getClientTickets(client_id);
    //     return ticketReservationService;
    // }   
}