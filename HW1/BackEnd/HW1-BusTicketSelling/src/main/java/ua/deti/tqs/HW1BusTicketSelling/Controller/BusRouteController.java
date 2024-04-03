package ua.deti.tqs.HW1BusTicketSelling.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    private final BusRouteService busRouteService;

    @GetMapping("/getBusRoute/{busRouteId}")
    public ResponseEntity<BusRoute> getBusRouteById(@PathVariable("busRouteId") String busRouteId) {
        return ResponseEntity.ok(busRouteService.getBusRouteById(busRouteId));
    }

    @GetMapping("/getBusRoutes")
    public ResponseEntity<?> getBusRoutes() {
        return ResponseEntity.ok(busRouteService.getBusRoutes());
    }

    @PostMapping("/searchBusRoute")
    public ResponseEntity<Map<String, Object>> searchBusRoute(@RequestBody BusRouteSearchDTO busRouteSearchDTO) {

        List<BusRoute> busRoutes = busRouteService.searchBusRoute(busRouteSearchDTO);
        
        if (busRoutes.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No bus routes found"));
        }

        Map<String, Object> response = Map.of("busRoutes", busRoutes);
 
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getExhangeRate/{currencyWanted}")
    public ResponseEntity<?> getExchangeRate(@PathVariable String currencyWanted) {
        System.out.println("currencyWanted: " + currencyWanted);
        return ResponseEntity.ok(busRouteService.getExchangeRate(currencyWanted));
    }

    @GetMapping("/getAllCurrencies")
    public ResponseEntity<?> getAllCurrencies() {
        return ResponseEntity.ok(busRouteService.getAllCurrencies());
    }
}