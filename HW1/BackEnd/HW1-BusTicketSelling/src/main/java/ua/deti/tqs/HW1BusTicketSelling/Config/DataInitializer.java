package ua.deti.tqs.HW1BusTicketSelling.Config;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import ua.deti.tqs.HW1BusTicketSelling.Entity.Bus;
import ua.deti.tqs.HW1BusTicketSelling.Entity.BusRoute;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRepository;
import ua.deti.tqs.HW1BusTicketSelling.Repository.BusRouteRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private BusRepository busRepository;
    private BusRouteRepository busRouteRepository;



    public DataInitializer(BusRepository busRepository, BusRouteRepository busRouteRepository) {
        this.busRepository = busRepository;
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[][] routes = {
            {"Lisbon", "Porto"},
            {"Porto", "Braga"},
            {"Coimbra", "Lisbon"},
            {"Lisbon", "Faro"},
            {"Porto", "Aveiro"},
            {"Braga", "Guimarães"},
            {"Faro", "Évora"},
            {"Évora", "Lisbon"},
            {"Coimbra", "Porto"},
            {"Aveiro", "Coimbra"}
        };

        String[][] buses = {
            {"AA-00-00", "Mercedes", "Model1", "56", "FlexiBus"},
            {"BB-11-11", "Mercedes", "Model2", "67", "Renex"},
            {"CC-22-22", "Mercedes", "Model2", "67", "Renex"},
            {"DD-33-33", "Mercedes", "Model1", "56", "FlexiBus"},
            {"EE-44-44", "Mercedes", "Model3", "70", "Rede Expressos"},
            {"FF-55-55", "Mercedes", "Model3", "70", "Rede Expressos"},
            {"GG-66-66", "Mercedes", "Model3", "70", "Rede Expressos"},
            {"HH-77-77", "Mercedes", "Model4", "60", "Renex"},
            {"II-88-88", "Mercedes", "Model5", "54", "FlexiBus"},
            {"JJ-99-99", "Mercedes", "Model5", "54", "FlexiBus"}
        };

        for (String[] bus : buses) {
            Bus newBus = new ua.deti.tqs.HW1BusTicketSelling.Entity.Bus();
            newBus.setBusLicensePlate(bus[0]);
            newBus.setBusBrand(bus[1]);
            newBus.setBusModel(bus[2]);
            newBus.setBusSeats(Integer.parseInt(bus[3]));
            newBus.setBusCompany(bus[4]);
            busRepository.save(newBus);
        }

        BusRoute newRoute = new BusRoute();
        newRoute.setRouteId("FLX123");
        newRoute.setDepartureCity("Lisboa");
        newRoute.setArrivalCity("Porto");
        newRoute.setDepartureTime(dateFormat.parse("2024-04-01 08:00"));
        newRoute.setArrivalTime(dateFormat.parse("2024-04-01 12:00"));
        newRoute.setDuration(240);
        newRoute.setPrice(20.0);
        newRoute.setBusSeatsAvailable(56);
        Bus bus = busRepository.findByBusLicensePlate("AA-00-00");
        newRoute.setBusId(bus.getBusId());
        newRoute.setBusInfo(bus);
        busRouteRepository.save(newRoute);

        BusRoute newRoute2 = new BusRoute();
        newRoute2.setRouteId("REN763");
        newRoute2.setDepartureCity("Porto");
        newRoute2.setArrivalCity("Lisboa");
        newRoute2.setDepartureTime(dateFormat.parse("2024-04-01 08:00"));
        newRoute2.setArrivalTime(dateFormat.parse("2024-04-01 12:00"));
        newRoute2.setDuration(240);
        newRoute2.setPrice(25.0);
        newRoute2.setBusSeatsAvailable(67);
        Bus bus2 = busRepository.findByBusLicensePlate("BB-11-11");
        newRoute2.setBusId(bus2.getBusId());
        newRoute2.setBusInfo(bus2);
        busRouteRepository.save(newRoute2);
        
    }
}