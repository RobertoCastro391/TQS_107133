package ua.deti.tqs.HW1BusTicketSelling.Service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRepository;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusService;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public Bus getBusById(int busId) {
        return busRepository.findById(busId).orElse(null);
    }

    @Override
    public List<Bus> getBuses() {
        return busRepository.findAll();
    }
    
}
