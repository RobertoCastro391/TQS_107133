package ua.deti.tqs.HW1BusTicketSelling.Repository;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Bus findByBusId(int busId);
    Bus findByBusLicensePlate(String busLicensePlate);
    List<Bus> findByBusCompany(String busCompany);
    Integer getBusSeatsByBusId(int busId);
}