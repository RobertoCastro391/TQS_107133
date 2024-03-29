package ua.deti.tqs.HW1BusTicketSelling.Service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRepository;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRouteRepository;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusRouteService;

@Service
public class BusRouteServiceImpl implements BusRouteService {
    private final BusRouteRepository busRouteRepository;
    private final BusRepository busRepository;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository, BusRepository busRepository) {
        this.busRouteRepository = busRouteRepository;
        this.busRepository = busRepository;
    }

    @Override
    public Boolean isBusAvailable(BusRoute busRoute) {
        Boolean isFull = busRoute.getBusSeatsAvailable() > 0;
        return isFull;
    }

    @Override
    public BusRoute getBusRouteById(String routeId) {
        return busRouteRepository.findByRouteId(routeId);
    }

    @Override
    public List<BusRoute> getBusRoutes() {
        return busRouteRepository.findAll();
    }
}