package ua.deti.tqs.hw1busticketselling.service;

import org.springframework.stereotype.Service;

import ua.deti.tqs.hw1busticketselling.dto.BusReservationDTO;
import ua.deti.tqs.hw1busticketselling.entity.BusRoute;
import ua.deti.tqs.hw1busticketselling.entity.Client;
import ua.deti.tqs.hw1busticketselling.entity.ReservationTicket;
import ua.deti.tqs.hw1busticketselling.repository.BusRouteRepository;
import ua.deti.tqs.hw1busticketselling.repository.ClientRepository;
import ua.deti.tqs.hw1busticketselling.repository.ReservationTicketRepository;

@Service
public class ReservationTicketService {

    private final ReservationTicketRepository reservationTicketRepository;
    private final BusRouteRepository busRouteRepository;
    private final ClientRepository clientRepository;

    public ReservationTicketService(ReservationTicketRepository reservationTicketRepository,
            BusRouteRepository busRouteRepository, ClientRepository clientRepository) {
        this.reservationTicketRepository = reservationTicketRepository;
        this.busRouteRepository = busRouteRepository;
        this.clientRepository = clientRepository;
    }

    public ReservationTicket createTicket(BusReservationDTO reservationDTO) {


        Client client = new Client();
        client.setClientName(reservationDTO.getClientName());
        client.setClientSurname(reservationDTO.getClientSurname());
        client.setClientEmail(reservationDTO.getClientEmail());
        client.setClientPhone(reservationDTO.getClientPhone());
        client.setClientAddress(reservationDTO.getClientAddress());
        client.setClientCity(reservationDTO.getClientCity());
        client.setClientCountry(reservationDTO.getClientCountry());
        client.setClientPostalCode(reservationDTO.getClientPostalCode());
        clientRepository.save(client);

        ReservationTicket reservation = new ReservationTicket();
        reservation.generateReservationId();
        reservation.setClientId(client.getClientId());
        reservation.setClient(client);
        reservation.setBusRouteId(reservationDTO.getRouteId());
        reservation.setBusRouteInfo(busRouteRepository.findByRouteId(reservationDTO.getRouteId()));
        reservation.setPrice(reservationDTO.getPrice());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setCreditCardNumber(reservationDTO.getCreditCardNumber());
        reservation.setCreditCardExpiration(reservationDTO.getCreditCardExpiration());
        reservation.setCreditCardCVV(reservationDTO.getCreditCardCVV());
        reservation.setReverStatus("Confirmed");

        BusRoute route = busRouteRepository.findByRouteId(reservationDTO.getRouteId());
        route.setBusSeatsAvailable(route.getBusSeatsAvailable() - 1);
        busRouteRepository.save(route);

        return reservationTicketRepository.save(reservation);
    }

    public ReservationTicket getTicketById(String ticketId) {
        return reservationTicketRepository.findByTicketId(ticketId);
    } 
}