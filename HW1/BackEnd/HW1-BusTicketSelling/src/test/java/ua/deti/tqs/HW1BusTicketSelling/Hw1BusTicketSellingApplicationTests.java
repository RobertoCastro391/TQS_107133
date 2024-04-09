package ua.deti.tqs.hw1busticketselling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class HW1BusTicketSellingApplicationTests {

    @Test
    void contextLoads() {
        assertThatCode(() -> HW1BusTicketSelling.main(new String[]{})).doesNotThrowAnyException();
    }
}
