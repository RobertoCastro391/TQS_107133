package ua.deti.tqs.HW1BusTicketSelling.Entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BusRoute")
public class BusRoute {

    @Id
    @Column(name = "Id")
    private String routeId;

    @Column(name = "DepartureCity")
    private String departureCity;

    @Column(name = "ArrivalCity")
    private String arrivalCity;

    @Column(name = "DepartureDate")
    private LocalDate departureDate;

    @Column(name = "DepartureTime")
    private Date departureTime;

    @Column(name = "ArrivalDate")
    private LocalDate arrivalDate;

    @Column(name = "ArrivalTime")
    private Date arrivalTime;

    @Column(name = "Duration")
    private int duration;

    @Column(name = "Price")
    private double price;

    @Column(name = "SeatsAvailable")
    private int busSeatsAvailable;

    @Column(name = "BusId", nullable = false)
    private int busId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BusId", referencedColumnName = "Id", insertable = false, updatable = false)
    private Bus busInfo;    
}