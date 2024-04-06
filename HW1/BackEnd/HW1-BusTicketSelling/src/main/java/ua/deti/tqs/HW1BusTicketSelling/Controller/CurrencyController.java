package ua.deti.tqs.HW1BusTicketSelling.Controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ua.deti.tqs.HW1BusTicketSelling.Service.CurrencyService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/currency")
public class CurrencyController {

    private static Logger log = LogManager.getLogger(BusController.class);
    
    @Autowired
    private CacheManager cacheManager;

    private final CurrencyService currencyService;
    
    @GetMapping("/getExhangeRate/{currencyWanted}")
    public ResponseEntity<?> getExchangeRate(@PathVariable String currencyWanted) {
        
        log.info("GET Echange Rate: EUR -> " + currencyWanted);
                
        String cacheKey = currencyWanted;
        CaffeineCache cache = (CaffeineCache) cacheManager.getCache("exchangeRates");
        
        if (cache != null && cache.getNativeCache().asMap().get(cacheKey) != null) {
            log.info("GET Echange Rate from cache");
        } else {
            log.info("GET Echange Rate from API");
        }
        
        currencyService.getExchangeRate(currencyWanted);
        return ResponseEntity.ok(currencyService.getExchangeRate(currencyWanted));
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

        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }   
}