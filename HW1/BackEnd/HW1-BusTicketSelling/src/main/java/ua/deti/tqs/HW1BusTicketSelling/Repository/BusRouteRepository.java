package ua.deti.tqs.HW1BusTicketSelling.Repository;

import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, String> {
    BusRoute findByRouteId(String routeId);
    List<BusRoute> findByDepartureCity(String departureCity);
    List<BusRoute> findByArrivalCity(String arrivalCity);
    List<BusRoute> findByDepartureCityAndArrivalCityAndDepartureDate(String departureCity, String arrivalCity, LocalDate departureDate);
    
} 