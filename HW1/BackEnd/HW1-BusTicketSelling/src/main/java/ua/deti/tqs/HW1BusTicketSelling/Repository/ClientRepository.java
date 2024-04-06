package ua.deti.tqs.HW1BusTicketSelling.Repository;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByClientId(int clientId);
    Client findByClientName(String clientName);
    int getClientIdByClientNameAndClientSurname(String clientName, String clientSurname);
}