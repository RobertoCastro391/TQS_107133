package ua.deti.tqs.HW1BusTicketSelling.Repository;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Client;
import ua.deti.tqs.HW1BusTicketSelling.Entity.ReservationTicket;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationTicketRepository extends JpaRepository<ReservationTicket, String> {
    ReservationTicket findByTicketId(String ticketId);
    List<ReservationTicket> findByClient(Client client);
}