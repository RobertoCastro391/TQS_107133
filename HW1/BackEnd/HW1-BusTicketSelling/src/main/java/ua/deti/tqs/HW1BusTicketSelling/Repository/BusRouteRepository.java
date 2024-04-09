package ua.deti.tqs.hw1busticketselling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.deti.tqs.hw1busticketselling.entity.BusRoute;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, String> {
    BusRoute findByRouteId(String routeId);
    List<BusRoute> findByDepartureCity(String departureCity);
    List<BusRoute> findByArrivalCity(String arrivalCity);
    List<BusRoute> findByDepartureCityAndArrivalCityAndDepartureDate(String departureCity, String arrivalCity, LocalDate departureDate);
    
} 