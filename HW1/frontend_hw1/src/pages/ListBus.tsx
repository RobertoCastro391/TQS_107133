import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

import Navbar from "../components/Navbar";
import Footer from "../components/Footer/Footer";
import CardBus from "../components/CardBus/CardBus";
import SeachComponent2 from "../components/SearchComponent2/SearchComponent2";

interface BusRoute {
  busInfo: {
    busCompany: string;
  };
  routeId: string;
  departureCity: string;
  arrivalCity: string;
  departureTime: string;
  arrivalTime: string;
  price: number;
}


const ListBus = () => {
  const location = useLocation();
  const { from, to, date } = location.state || {};
  const [busRoutes, setBusRoutes] = useState<BusRoute[]>([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      const requestBody = {
        departureCity: from,
        arrivalCity: to,
        date: date,
      };

      try {
        const response = await fetch(
          "http://localhost:8080/api/busRoute/searchBusRoute",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody),
          }
        );

        const data = await response.json();

        console.log("Fetched data:", data);

        if (data.busRoutes) {
          setBusRoutes(data.busRoutes);
        } else {
          setBusRoutes([]);
        }
      } catch (error) {
        console.error("Failed to fetch bus routes:", error);
        setBusRoutes([]);
      } finally {
        setIsLoading(false);
      }
    };

    if (from && to && date) {
      fetchData();
    }
  }, [from, to, date]);

  return (
    <>
      <Navbar />
      <div style={{ padding: "2%" }}>
        <SeachComponent2 from={from} to={to} date={date} />
      </div>
      <div
        style={{
          padding: "2%",
          display: "flex",
          flexDirection: "column",
          gap: "20px",
        }}
      >
        {isLoading ? (
          <p style={{ fontSize: "400%" }}>Loading...</p>
        ) : busRoutes.length === 0 ? (
          <p style={{ fontSize: "200%" }}>
            No buses found that match your search criteria.
          </p>
        ) : (
          <>
            <p style={{ fontSize: "200%" }}>
              We have found {busRoutes.length} buses that match your search:
            </p>
            {busRoutes.map((busRoute, key) => (
              <CardBus
                key={key}
                companyName={busRoute.busInfo.busCompany}
                busNumber={busRoute.routeId}
                departureCity={busRoute.departureCity}
                arrivalCity={busRoute.arrivalCity}
                departureTime={busRoute.departureTime}
                arrivalTime={busRoute.arrivalTime}
                fare={busRoute.price}
              />
            ))}
          </>
        )}
      </div>
      <Footer />
    </>
  );
};

export default ListBus;
