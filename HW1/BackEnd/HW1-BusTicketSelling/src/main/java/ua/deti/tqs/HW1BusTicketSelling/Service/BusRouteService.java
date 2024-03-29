package ua.deti.tqs.HW1BusTicketSelling.Service;

import java.util.List;

import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;

public interface BusRouteService {
    Boolean isBusAvailable(BusRoute busRoute);
    BusRoute getBusRouteById(String routeId);
    List<BusRoute> getBusRoutes();
    
}
