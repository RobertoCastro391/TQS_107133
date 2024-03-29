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
@Table(name = "Bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int busId;

    @Column(name = "LicensePlate")
    private String busLicensePlate;

    @Column(name = "Brand")
    private String busBrand;

    @Column(name = "Model")
    private String busModel;

    @Column(name = "Seats")
    private int busSeats;

    @Column(name = "Company")
    private String busCompany;
}