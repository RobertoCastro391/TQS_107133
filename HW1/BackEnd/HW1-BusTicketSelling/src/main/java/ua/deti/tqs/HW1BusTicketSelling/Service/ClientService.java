package ua.deti.tqs.HW1BusTicketSelling.Service;

import org.springframework.stereotype.Service;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Client;
import ua.deti.tqs.HW1BusTicketSelling.Repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    public Client getClientByNameAndSurname(String clientName, String clientSurname) {
        return clientRepository.getClientByClientNameAndClientSurname(clientName, clientSurname);
    }
}  