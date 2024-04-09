package ua.deti.tqs.hw1busticketselling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.deti.tqs.hw1busticketselling.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByClientId(int clientId);
    Client findByClientName(String clientName);
    Client getClientByClientNameAndClientSurname(String clientName, String clientSurname);
}