package ua.deti.tqs.HW1BusTicketSelling.Service;

import org.springframework.stereotype.Service;

import ua.deti.tqs.HW1BusTicketSelling.Repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    public int getClientIdByNameAndSurname(String clientName, String clientSurname) {
        return clientRepository.getClientIdByClientNameAndClientSurname(clientName, clientSurname);
    }
}