package ua.deti.tqs.HW1BusTicketSelling.Service;

import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyService {

        @Cacheable(value = "exchangeRates", key = "#currencyWanted")
        public Map<String, Object> getExchangeRate(String currencyWanted) {

                WebClient webClient = WebClient.create("https://api.freecurrencyapi.com");

                return webClient.get()
                                .uri(uriBuilder -> uriBuilder.path("/v1/latest")
                                                .queryParam("apikey",
                                                                "fca_live_NHtYynIhnS7wMEhNIjiCsQHI6xzux5gg3cBADmLJ")
                                                .queryParam("base_currency", "EUR")
                                                .queryParam("currencies", currencyWanted)
                                                .build())
                                .retrieve()
                                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                                })
                                .block();
        }

        @Cacheable(value = "allCurrencies")
        public Map<String, Object> getAllCurrencies() {
                WebClient webClient = WebClient.create("https://api.freecurrencyapi.com");

                return webClient.get()
                                .uri(uriBuilder -> uriBuilder.path("/v1/currencies")
                                                .queryParam("apikey",
                                                                "fca_live_NHtYynIhnS7wMEhNIjiCsQHI6xzux5gg3cBADmLJ")
                                                .build())
                                .retrieve()
                                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                                })
                                .block();
        }

}