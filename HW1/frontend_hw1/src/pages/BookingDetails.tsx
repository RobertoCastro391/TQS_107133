import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer/Footer";
import "bootstrap/dist/css/bootstrap.min.css";
import BookingDetailsComponent from "../components/BookingDetailsComponent";

interface Reservation {
  ticketId?: string;
  clientId?: number;
  client?: {
    clientId?: number;
    clientName?: string;
    clientSurname?: string;
    clientEmail?: string;
  };
  busRouteId?: number;
  busRouteInfo?: {
    routeId?: string;
    departureCity?: string;
    arrivalCity?: string;
    departureTime?: string;
    arrivalTime?: string;
  };
  fare?: number;
  reservationDate?: Date;
  creditCardNumber?: string;
  creditCardExpiration?: string;
  creditCardCVV?: string;
  reverStatus?: string;
}

const BookingDetails = () => {
  const location = useLocation();
  const reservationId = location.state?.reservationId;

  const [reservation, setReservation] = useState<Reservation>({});

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/reservation/getReservation/${reservationId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        const data = await response.json();

        console.log("Fetched reservation data:", data);

        if (data) {
          const mappedData: Reservation = {
            ticketId: data.ticketId,
            clientId: data.clientId,
            client: data.client
              ? {
                  clientId: data.client.clientId,
                  clientName: data.client.clientName,
                  clientSurname: data.client.clientSurname,
                  clientEmail: data.client.clientEmail,
                }
              : undefined,
            busRouteId: data.busRouteId,
            busRouteInfo: data.busRouteInfo
              ? {
                  routeId: data.busRouteInfo.routeId,
                  departureCity: data.busRouteInfo.departureCity,
                  arrivalCity: data.busRouteInfo.arrivalCity,
                  departureTime: data.busRouteInfo.departureTime,
                  arrivalTime: data.busRouteInfo.arrivalTime,
                }
              : undefined,
            fare: data.price,
            reservationDate: data.reservationDate,
            creditCardNumber: data.creditCardNumber,
            creditCardExpiration: data.creditCardExpiration,
            creditCardCVV: data.creditCardCVV,
            reverStatus: data.reverStatus,
          };

          setReservation(mappedData);
        } else {
          setReservation({});
        }
      } catch (error) {
        console.error("Failed to fetch reservation:", error);
        setReservation({});
      }
    };

    if (reservationId) {
      fetchData();
    }
  }, [reservationId]);

  return (
    <>
      <Navbar />
        <BookingDetailsComponent reservationId={reservationId} reservation={reservation} />
      <Footer />
    </>
  );
};

export default BookingDetails;
