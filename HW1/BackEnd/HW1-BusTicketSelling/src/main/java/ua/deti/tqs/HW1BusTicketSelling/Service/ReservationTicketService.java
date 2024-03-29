package ua.deti.tqs.HW1BusTicketSelling.Service;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusReservationDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.ReservationTicket;
import java.util.List;

public interface ReservationTicketService {
    ReservationTicket createTicket(BusReservationDTO reservationDTO);
    ReservationTicket getTicketById(String ticket_id);
    List<ReservationTicket> getTicketsByClient(int client_id);
}