package ua.deti.tqs.HW1BusTicketSelling.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import ua.deti.tqs.HW1BusTicketSelling.DTO.BusRouteSearchDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRouteRepository;

@Service
public class BusRouteService {
    private final BusRouteRepository busRouteRepository;

    public BusRouteService(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    public Boolean isBusAvailable(BusRoute busRoute) {
        Boolean isFull = busRoute.getBusSeatsAvailable() > 0;
        return isFull;
    }

    public BusRoute getBusRouteById(String routeId) {
        return busRouteRepository.findByRouteId(routeId);
    }

    public List<BusRoute> getBusRoutes() {
        return busRouteRepository.findAll();
    }

    public List<BusRoute> searchBusRoute(BusRouteSearchDTO busRouteSearchDTO) {
        return busRouteRepository.findByDepartureCityAndArrivalCityAndDepartureDate(busRouteSearchDTO.getDepartureCity(),
                busRouteSearchDTO.getArrivalCity(), busRouteSearchDTO.getDate());
    }
}