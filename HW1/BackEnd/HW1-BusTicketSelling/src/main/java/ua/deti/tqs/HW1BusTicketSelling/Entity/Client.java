package ua.deti.tqs.HW1BusTicketSelling.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int clientId;

    @Column(name = "Name")
    private String clientName;

    @Column(name = "Surname")
    private String clientSurname;

    @Column(name = "Email")
    private String clientEmail;

    @Column(name = "Address")
    private String clientAddress;

    @Column(name = "PostalCode")
    private String clientPostalCode;

    @Column(name = "City")
    private String clientCity;

    @Column(name = "Country")
    private String clientCountry;

    @Column(name = "Phone")
    private String clientPhone;

}