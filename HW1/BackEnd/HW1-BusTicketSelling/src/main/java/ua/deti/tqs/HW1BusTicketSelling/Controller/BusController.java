package ua.deti.tqs.hw1busticketselling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.*;

import lombok.AllArgsConstructor;
import ua.deti.tqs.hw1busticketselling.entity.Bus;
import ua.deti.tqs.hw1busticketselling.service.BusService;


@RestController
@AllArgsConstructor
@RequestMapping("/api/bus")
public class BusController {

    private static Logger log = LogManager.getLogger(BusController.class);
    BusService busService;

    @GetMapping("/getBus/{busId}")
    public ResponseEntity<Bus> getBusById(@PathVariable("busId") int busId) {
        log.info("GET /api/bus/getBus/{}", busId);
        return ResponseEntity.ok(busService.getBusById(busId));
    }

    @GetMapping("/getBuses")
    public ResponseEntity<Object> getBuses() {
        log.info("GET /api/bus/getBuses");
        return ResponseEntity.ok(busService.getBuses());
    }
}