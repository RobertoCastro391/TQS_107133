package ua.deti.tqs.HW1BusTicketSelling.Entity;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ReservationTicket")
public class ReservationTicket {

    @Id
    @Column(name = "Id")
    private String ticketId;

    @Column(name = "ClientId", nullable = false)
    private int clientId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClientId", referencedColumnName = "Id", insertable = false, updatable = false)
    private Client client;

    @Column(name = "BusRouteId", nullable = false)
    private String busRouteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BusRouteId", referencedColumnName = "Id", insertable = false, updatable = false)
    private BusRoute busRouteInfo;

    @Column(name = "Price")
    private double price;

    @Column(name = "RerervationDate")
    private Date reservationDate;

    @Column(name = "CreditCardNumber")
    private String creditCardNumber;

    @Column(name = "CreditCardExpiration")
    private String creditCardExpiration;

    @Column(name = "CreditCardCVV")
    private String creditCardCVV;

    @Column(name = "ReverStatus")
    private String reverStatus;

    @PrePersist
    public void generateReservationId() {
        this.ticketId = generateRandomId();
    }

    private String generateRandomId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder(5);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }
}