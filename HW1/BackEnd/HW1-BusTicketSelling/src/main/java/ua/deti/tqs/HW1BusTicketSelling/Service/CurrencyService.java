package ua.deti.tqs.HW1BusTicketSelling.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CurrencyService {

    private static final Logger log = LogManager.getLogger(CurrencyService.class);

    private final Cache<String, Map<String, Object>> exchangeRatesCache;
    private final Cache<String, Map<String, Object>> allCurrenciesCache;

    @Value("${app.currency-api.key}")
    private String apiKey;

    private final WebClient webClient;

    public CurrencyService(@Value("${app.currency-api.base-url}") String apiBaseUrl) {
        this.webClient = WebClient.builder()
                                  .baseUrl(apiBaseUrl)
                                  .build();

        this.exchangeRatesCache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();

        this.allCurrenciesCache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    public Map<String, Object> getExchangeRate(String currencyWanted) {        
        Map<String, Object> rate = exchangeRatesCache.getIfPresent(currencyWanted);
        if (rate != null) {
            log.info("Fetching exchange rate from Cache: {}", currencyWanted);
            return rate;
        } else {
            log.info("Fetching exchange rate from API: {}", currencyWanted);
            rate = fetchExchangeRateFromAPI(currencyWanted);
            exchangeRatesCache.put(currencyWanted, rate);
            return rate;
        }
    }

    private Map<String, Object> fetchExchangeRateFromAPI(String currencyWanted) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/latest")
                        .queryParam("apikey", apiKey)
                        .queryParam("base_currency", "EUR")
                        .queryParam("currencies", currencyWanted)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }

    public Map<String, Object> getAllCurrencies() {
        Map<String, Object> currencies = allCurrenciesCache.getIfPresent("allCurrencies");
        if (currencies != null) {
            log.info("Fetching all currencies from Cache");
            return currencies;
        } else {
            log.info("Fetching all currencies from API");
            currencies = fetchAllCurrenciesFromAPI();
            allCurrenciesCache.put("allCurrencies", currencies);
            return currencies;
        }
    }

    private Map<String, Object> fetchAllCurrenciesFromAPI() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/currencies")
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }
}
