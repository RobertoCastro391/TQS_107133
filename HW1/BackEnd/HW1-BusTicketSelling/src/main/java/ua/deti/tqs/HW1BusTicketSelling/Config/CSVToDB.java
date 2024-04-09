package ua.deti.tqs.HW1BusTicketSelling.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRepository;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRouteRepository;

@Component
public class CSVToDB implements CommandLineRunner {

    public String csvBuses = "src/main/resources/buses.csv";
    public String csvBusRoutes = "src/main/resources/bus_routes.csv";

    private BusRepository busRepository;
    private BusRouteRepository busRouteRepository;

    public CSVToDB(BusRepository busRepository, BusRouteRepository busRouteRepository) {
        this.busRepository = busRepository;
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBuses();
        loadBusRoutes();
    }

    public void loadBuses() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(csvBuses))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (busRepository.findByBusLicensePlate(data[0]) != null) {
                    continue;
                }
                Bus bus = new Bus();
                bus.setBusLicensePlate(data[0]);
                bus.setBusBrand(data[1]);
                bus.setBusModel(data[2]);
                bus.setBusSeats(Integer.parseInt(data[3]));
                bus.setBusCompany(data[4]);
                busRepository.save(bus);
            }
        }
    }

    public void loadBusRoutes() throws Exception {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(csvBusRoutes))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (busRouteRepository.findByRouteId(data[0]) != null) {
                    continue;
                }

                BusRoute route = new BusRoute();
                route.setRouteId(data[0]);
                route.setDepartureCity(data[1]);
                route.setArrivalCity(data[2]);
                route.setDepartureDate(LocalDate.parse(data[3], dateFormatter));
                route.setDepartureTime(timeFormat.parse(data[4]));
                route.setArrivalDate(LocalDate.parse(data[5], dateFormatter));
                route.setArrivalTime(timeFormat.parse(data[6]));
                route.setDuration(Integer.parseInt(data[7]));
                route.setPrice(Double.parseDouble(data[8]));

                Bus bus = busRepository.findByBusLicensePlate(data[9]);
                route.setBusId(bus.getBusId());
                route.setBusInfo(bus);
                route.setBusSeatsAvailable(bus.getBusSeats());

                busRouteRepository.save(route);
            }
        }
    }
}