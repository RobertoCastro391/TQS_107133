package ua.deti.tqs.hw1busticketselling.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusReservationDTO {
    private String routeId;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String clientAddress;
    private String clientPostalCode;
    private String clientCity;
    private String clientCountry;
    private String clientPhone;
    private Date reservationDate;
    private String creditCardNumber;
    private String creditCardExpiration;
    private String creditCardCVV;
    private double price; 
}