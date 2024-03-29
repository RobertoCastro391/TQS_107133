package ua.deti.tqs.HW1BusTicketSelling.Service;

import java.util.List;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;

public interface BusService {
    Bus getBusById(int busId);  
    List<Bus> getBuses();
}
