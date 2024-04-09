package ua.deti.tqs.hw1busticketselling.service;

import org.springframework.stereotype.Service;

import ua.deti.tqs.hw1busticketselling.entity.Client;
import ua.deti.tqs.hw1busticketselling.repository.ClientRepository;

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