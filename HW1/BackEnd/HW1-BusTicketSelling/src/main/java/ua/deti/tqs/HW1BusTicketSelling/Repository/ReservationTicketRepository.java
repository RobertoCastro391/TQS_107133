package ua.deti.tqs.hw1busticketselling.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.deti.tqs.hw1busticketselling.entity.Client;
import ua.deti.tqs.hw1busticketselling.entity.ReservationTicket;

@Repository
public interface ReservationTicketRepository extends JpaRepository<ReservationTicket, String> {
    ReservationTicket findByTicketId(String ticketId);
    List<ReservationTicket> findByClient(Client client);
}