package ua.deti.tqs.hw1busticketselling.controller;

import lombok.AllArgsConstructor;
import ua.deti.tqs.hw1busticketselling.service.CurrencyService;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/currency")
public class CurrencyController {

    private static final Logger log = LogManager.getLogger(CurrencyController.class);
    
    private final CurrencyService currencyService;
    
    @GetMapping("/getExchangeRate/{currencyWanted}")
    public ResponseEntity<Object> getExchangeRate(@PathVariable String currencyWanted) {
        log.info("GET Exchange Rate: EUR -> {}", currencyWanted);
        Map<String, Object> response = currencyService.getExchangeRate(currencyWanted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllCurrencies")
    public ResponseEntity<Object> getAllCurrencies() {
        log.info("GET All Currencies");
        Map<String, Object> response = currencyService.getAllCurrencies();
        return ResponseEntity.ok(response);
    }   
}
