package ua.deti.tqs.hw1busticketselling.integrationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ua.deti.tqs.hw1busticketselling.service.CurrencyService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;


    String url = "/api/currency";

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("GET Exchange Rate - Successful")
    void whenGetExchangeRate_thenReturnsRate() throws Exception {
        String currency = "USD";
        double exchangeRateValue = 1.1;
        Map<String, Object> exchangeRateData = Collections.singletonMap("code", exchangeRateValue);
        Map<String, Object> exchangeRateResponse = Collections.singletonMap("data", exchangeRateData);

        Mockito.when(currencyService.getExchangeRate(currency)).thenReturn(exchangeRateResponse);

        mockMvc.perform(get(url + "/getExchangeRate/{currencyWanted}", currency)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value(exchangeRateValue));
    }


    @Test
    @DisplayName("GET All Currencies - Successful")
    void whenGetAllCurrencies_thenReturnsCurrencies() throws Exception {
        Map<String, Object> expectedCurrencies = new HashMap<>();
        expectedCurrencies.put("USD", "United States Dollar");
        expectedCurrencies.put("EUR", "Euro");
        expectedCurrencies.put("GBP", "British Pound");

        Mockito.when(currencyService.getAllCurrencies()).thenReturn(expectedCurrencies);

        mockMvc.perform(get(url + "/getAllCurrencies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.USD").value("United States Dollar"))
                .andExpect(jsonPath("$.EUR").value("Euro"))
                .andExpect(jsonPath("$.GBP").value("British Pound"));
    }
}