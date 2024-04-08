package ua.deti.tqs.HW1BusTicketSelling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
public class Hw1BusTicketSellingApplicationTests {

    @Test
    void contextLoads() {
        assertThatCode(() -> Hw1BusTicketSellingApplication.main(new String[]{})).doesNotThrowAnyException();
    }
}
