package ua.deti.tqs.hw1busticketselling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.deti.tqs.hw1busticketselling.dto.BusRouteSearchDTO;
import ua.deti.tqs.hw1busticketselling.entity.BusRoute;
import ua.deti.tqs.hw1busticketselling.repository.BusRouteRepository;

@Service
public class BusRouteService {
    private final BusRouteRepository busRouteRepository;

    public BusRouteService(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    public Boolean isBusAvailable(BusRoute busRoute) {
        return busRoute.getBusSeatsAvailable() > 0;
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