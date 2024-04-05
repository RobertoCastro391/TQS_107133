package ua.deti.tqs.HW1BusTicketSelling.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.*;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;

import ua.deti.tqs.HW1BusTicketSelling.DTO.BusRouteSearchDTO;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusRouteService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/busRoute")
public class BusRouteController {

    private static Logger log = LogManager.getLogger(BusRouteController.class);
    private final BusRouteService busRouteService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/getBusRoute/{busRouteId}")
    public ResponseEntity<BusRoute> getBusRouteById(@PathVariable("busRouteId") String busRouteId) {
        log.info("GET BusRoutes by busRouteId");
        BusRoute response = busRouteService.getBusRouteById(busRouteId);
        log.info("Response: " + busRouteService.getBusRouteById(busRouteId));
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBusRoutes")
    public ResponseEntity<?> getBusRoutes() {
        log.info("Get All BusRoutes");
        return ResponseEntity.ok(busRouteService.getBusRoutes());
    }

    @PostMapping("/searchBusRoute")
    public ResponseEntity<Map<String, Object>> searchBusRoute(@RequestBody BusRouteSearchDTO busRouteSearchDTO) {
        log.info("GET Bus Routes by: " + busRouteSearchDTO.getDepartureCity() + ", " + busRouteSearchDTO.getArrivalCity() + ", " + busRouteSearchDTO.getDate());
        List<BusRoute> busRoutes = busRouteService.searchBusRoute(busRouteSearchDTO);
        log.info("Response: " + busRoutes);
        
        if (busRoutes.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No bus routes found"));
        }

        Map<String, Object> response = Map.of("busRoutes", busRoutes);
 
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getExhangeRate/{currencyWanted}")
    public ResponseEntity<?> getExchangeRate(@PathVariable String currencyWanted) {
        
        log.info("GET Echange Rate: EUR -> " + currencyWanted);
                
        String cacheKey = currencyWanted;
        CaffeineCache cache = (CaffeineCache) cacheManager.getCache("exchangeRates");
        
        if (cache != null && cache.getNativeCache().asMap() != null) {
            log.info("GET Echange Rate from cache");
        } else {
            log.info("GET Echange Rate from API");
        }
        
        busRouteService.getExchangeRate(currencyWanted);
        return ResponseEntity.ok(busRouteService.getExchangeRate(currencyWanted));
    }

    @GetMapping("/getAllCurrencies")
    public ResponseEntity<?> getAllCurrencies() {
        log.info("GET All Currencies");

        CaffeineCache cache = (CaffeineCache) cacheManager.getCache("allCurrencies");

        if (cache != null && cache.getNativeCache().asMap() != null) {
            log.info("GET All Currencies from cache");
        } else {
            log.info("GET All Currencies from API");
        }

        return ResponseEntity.ok(busRouteService.getAllCurrencies());
    }
}