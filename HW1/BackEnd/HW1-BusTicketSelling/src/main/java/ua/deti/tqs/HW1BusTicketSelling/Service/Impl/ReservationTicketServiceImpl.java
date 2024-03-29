package ua.deti.tqs.HW1BusTicketSelling.Service.Impl;

import java.util.List;


import org.springframework.stereotype.Service;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusReservationDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Entity.Client;
import ua.deti.tqs.HW1BusTicketSelling.Entity.ReservationTicket;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRouteRepository;
import ua.deti.tqs.HW1BusTicketSelling.Repository.ClientRepository;
import ua.deti.tqs.HW1BusTicketSelling.Repository.ReservationTicketRepository;
import ua.deti.tqs.HW1BusTicketSelling.Service.ReservationTicketService;

@Service
public class ReservationTicketServiceImpl implements ReservationTicketService {

    private final ReservationTicketRepository reservationTicketRepository;
    private final BusRouteRepository busRouteRepository;
    private final ClientRepository clientRepository;

    public ReservationTicketServiceImpl(ReservationTicketRepository reservationTicketRepository,
            BusRouteRepository busRouteRepository, ClientRepository clientRepository) {
        this.reservationTicketRepository = reservationTicketRepository;
        this.busRouteRepository = busRouteRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public ReservationTicket createTicket(BusReservationDTO reservationDTO) {

        System.out.println("Creating ticket");

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

        System.out.println("Ticket created");
        System.out.println("Route seats available: " + route.getBusSeatsAvailable());

        return reservationTicketRepository.save(reservation);
    }

    @Override
    public ReservationTicket getTicketById(String ticket_id) {
        return reservationTicketRepository.findByTicketId(ticket_id);
    }

    @Override
    public List<ReservationTicket> getTicketsByClient(int client_id) {
        Client client = clientRepository.findByClientId(client_id);
        return reservationTicketRepository.findByClient(client);
    }
}