package ua.deti.tqs.HW1BusTicketSelling.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRepository;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus getBusById(int busId) {
        return busRepository.findById(busId).orElse(null);
    }

    public List<Bus> getBuses() {
        return busRepository.findAll();
    }
}