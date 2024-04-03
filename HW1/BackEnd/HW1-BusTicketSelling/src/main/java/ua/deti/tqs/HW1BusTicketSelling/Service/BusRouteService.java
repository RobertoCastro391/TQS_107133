package ua.deti.tqs.HW1BusTicketSelling.Service;

import java.util.List;
import java.util.Map;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusRouteSearchDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;

public interface BusRouteService {
    Boolean isBusAvailable(BusRoute busRoute);
    BusRoute getBusRouteById(String routeId);
    List<BusRoute> searchBusRoute(BusRouteSearchDTO busRouteSearchDTO);
    List<BusRoute> getBusRoutes();
    Map<String, Object> getExchangeRate(String currencyWanted);
    Map<String, Object> getAllCurrencies();
}