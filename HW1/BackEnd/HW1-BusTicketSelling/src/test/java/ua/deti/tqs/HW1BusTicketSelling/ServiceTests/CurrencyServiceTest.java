package ua.deti.tqs.hw1busticketselling.serviceTests;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import ua.deti.tqs.hw1busticketselling.service.CurrencyService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    private MockWebServer mockWebServer;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        currencyService = new CurrencyService(baseUrl);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("Test getExchangeRate - From API")
    void testGetExchangeRate_FROM_API() {
        String response = "{\"data\": {\"CHF\": 0.97}}";

        mockWebServer.enqueue(new MockResponse()
                .setBody(response)
                .addHeader("Content-Type", "application/json"));

        Map<String, Object> result = currencyService.getExchangeRate("CHF");

        assertTrue(result.containsKey("data"));
        assertEquals(1, mockWebServer.getRequestCount());
    }

    @Test
    @DisplayName("Test getExchangeRate - From Cache")
    void testGetExchangeRate_FromCache() {
        String response = "{\"data\": {\"CHF\": 0.97}}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(response)
                .addHeader("Content-Type", "application/json"));

        Map<String, Object> initialResult = currencyService.getExchangeRate("CHF");
        assertTrue(initialResult.containsKey("data"));

        Map<String, Object> cachedResult = currencyService.getExchangeRate("CHF");
        assertTrue(cachedResult.containsKey("data"));

        assertEquals(1, mockWebServer.getRequestCount());
    }

    @Test
    @DisplayName("Test getAllCurrencies - From API")
    void testGetAllCurrencies_FROM_API() {
        String response = "{\"CHF\": \"Swiss Franc\", \"EUR\": \"Euro\", \"BRL\": \"Brazilian Real\"}";

        mockWebServer.enqueue(new MockResponse()
                .setBody(response)
                .addHeader("Content-Type", "application/json"));

        Map<String, Object> result = currencyService.getAllCurrencies();

        assertTrue(result.containsKey("CHF"));
        assertTrue(result.containsKey("EUR"));
        assertTrue(result.containsKey("BRL"));

        assertEquals("Swiss Franc", result.get("CHF"));
        assertEquals("Euro", result.get("EUR"));
        assertEquals("Brazilian Real", result.get("BRL"));
    }

    @Test
    @DisplayName("Test getAllCurrencies - From Cache")
    void testGetAllCurencies_FromCache() {
        String response = "{\"CHF\": \"Swiss Franc\", \"EUR\": \"Euro\", \"BRL\": \"Brazilian Real\"}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(response)
                .addHeader("Content-Type", "application/json"));

        Map<String, Object> initialResult = currencyService.getAllCurrencies();
        assertTrue(initialResult.containsKey("CHF"));
        assertTrue(initialResult.containsKey("EUR"));
        assertTrue(initialResult.containsKey("BRL"));

        Map<String, Object> cachedResult = currencyService.getAllCurrencies();
        assertTrue(cachedResult.containsKey("CHF"));
        assertTrue(cachedResult.containsKey("EUR"));
        assertTrue(cachedResult.containsKey("BRL"));

        assertEquals(1, mockWebServer.getRequestCount());
    }
}