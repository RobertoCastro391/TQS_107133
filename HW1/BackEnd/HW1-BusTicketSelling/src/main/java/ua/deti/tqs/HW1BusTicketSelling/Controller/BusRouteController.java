package ua.deti.tqs.HW1BusTicketSelling.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
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
}
