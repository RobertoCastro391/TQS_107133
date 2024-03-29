package ua.deti.tqs.HW1BusTicketSelling.Service.Impl;

import org.springframework.stereotype.Service;

import ua.deti.tqs.HW1BusTicketSelling.Repository.ClientRepository;
import ua.deti.tqs.HW1BusTicketSelling.Service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    @Override
    public int getClientIdByNameAndSurname(String clientName, String clientSurname) {
        return clientRepository.getClientIdByClientNameAndClientSurname(clientName, clientSurname);
    }

}