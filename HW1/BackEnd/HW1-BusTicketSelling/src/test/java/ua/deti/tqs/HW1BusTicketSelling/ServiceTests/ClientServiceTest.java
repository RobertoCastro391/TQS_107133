package ua.deti.tqs.hw1busticketselling.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.deti.tqs.hw1busticketselling.entity.Client;
import ua.deti.tqs.hw1busticketselling.repository.ClientRepository;
import ua.deti.tqs.hw1busticketselling.service.ClientService;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock(lenient = true)
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    @Test
    @DisplayName("Test Get Client by name and surname")
    void testGetClientByNameAndSurname() {
        Client client = new Client(1, "Joao", "Silva", "joaosilva@gmail.com", "Rua do Joao", "4000-000", "Porto", "Portugal", "912345678");
    
        when(clientRepository.getClientByClientNameAndClientSurname("Joao", "Silva")).thenReturn(client);

        assertEquals(client, clientService.getClientByNameAndSurname("Joao", "Silva"));

        verify(clientRepository, times(1)).getClientByClientNameAndClientSurname("Joao", "Silva");
    
    }
}
