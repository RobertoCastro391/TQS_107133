import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.example.IStockmarketService;
import org.example.Stock;
import org.example.StocksPortfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {
    
    @InjectMocks
    StocksPortfolio stocksPortfolio;

    @Mock
    IStockmarketService stockMarketService;

    @BeforeEach
    public void setUp() {
        stocksPortfolio = new StocksPortfolio(stockMarketService);
    }

    @DisplayName("Test for the method getTotalValue")
    @Test
    public void getTotalValueTest() {

        Stock stock1 = new Stock("SONAE", 10);
        Stock stock2 = new Stock("EDP", 20);
        stocksPortfolio.addStock(stock1);
        stocksPortfolio.addStock(stock2);

        when(stockMarketService.lookUpPrice("SONAE")).thenReturn(100.0);
        when(stockMarketService.lookUpPrice("EDP")).thenReturn(200.0);

        double result = 10*100.0 + 20*200.0;
        // assertEquals(result, stocksPortfolio.totalValue());
        assertThat(stocksPortfolio.totalValue(), equalTo(result));
        
        verify(stockMarketService, times(2)).lookUpPrice(anyString());
    }
}