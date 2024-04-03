package ua.deti.tqs.HW1BusTicketSelling.Service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusRouteSearchDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRepository;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRouteRepository;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusRouteService;

@Service
public class BusRouteServiceImpl implements BusRouteService {
    private final BusRouteRepository busRouteRepository;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
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

    @Override
    public List<BusRoute> searchBusRoute(BusRouteSearchDTO busRouteSearchDTO) {
        
        System.out.println("Aquiii");
        System.out.println(busRouteSearchDTO.getDepartureCity());
        System.out.println(busRouteSearchDTO.getArrivalCity());
        System.out.println(busRouteSearchDTO.getDate());
        
        return busRouteRepository.findByDepartureCityAndArrivalCityAndDepartureDate(busRouteSearchDTO.getDepartureCity(),
                busRouteSearchDTO.getArrivalCity(), busRouteSearchDTO.getDate());
    }

    @Cacheable(value = "exchangeRates", key = "#currencyWanted")
    @Override
    public Map<String, Object> getExchangeRate(String currencyWanted) {
        WebClient webClient = WebClient.create("https://api.freecurrencyapi.com");
        
        // Adjust the URI as per your requirement
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/v1/latest")
                        .queryParam("apikey", "fca_live_NHtYynIhnS7wMEhNIjiCsQHI6xzux5gg3cBADmLJ")
                        .queryParam("base_currency", "EUR")
                        .queryParam("currencies", currencyWanted)
                        .build())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .block();
    }

    @Cacheable(value = "allCurrencies")
    @Override
    public Map<String, Object> getAllCurrencies() {
        WebClient webClient = WebClient.create("https://api.freecurrencyapi.com");
        
        // Adjust the URI as per your requirement
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/v1/currencies")
                        .queryParam("apikey", "fca_live_NHtYynIhnS7wMEhNIjiCsQHI6xzux5gg3cBADmLJ")
                        .build())
                        .retrieve()
                        // Use ParameterizedTypeReference to preserve generic type information
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .block(); // Be aware that block() is synchronous
    }
}