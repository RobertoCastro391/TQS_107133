package ua.deti.tqs.HW1BusTicketSelling.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusRouteSearchDTO {
    private String departureCity;
    private String arrivalCity;
    private LocalDate date;
}