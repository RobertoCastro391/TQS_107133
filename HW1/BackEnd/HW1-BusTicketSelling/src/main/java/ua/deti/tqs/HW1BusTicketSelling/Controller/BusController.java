package ua.deti.tqs.HW1BusTicketSelling.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Service.BusService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bus")
public class BusController {

    public BusService busService;

    @GetMapping("/getBus/{busId}")
    public ResponseEntity<Bus> getBusById(@PathVariable("busId") int busId) {
        return ResponseEntity.ok(busService.getBusById(busId));
    }

    @GetMapping("/getBuses")
    public ResponseEntity<?> getBuses() {
        return ResponseEntity.ok(busService.getBuses());
    }


    
}
