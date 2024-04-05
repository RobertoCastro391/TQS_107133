import { useCurrency } from "../CurrencyContext";


interface props {
  reservationId: string;
  reservation: {
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
  };
}

const BookingDetailsComponent = ({ reservationId, reservation }: props) => {

  const { currency } = useCurrency();

  const exchangeRate = currency.exchangeRate.currencyCode;
  const currencySymbol = currency.symbol;
  const fare = reservation.fare ?? 0;
  const price = (fare * exchangeRate).toFixed(2);

  return (
    <div style={{ fontSize: "150%", padding: "2%" }}>
      <h1>Booking Details</h1>
      <div className="container mt-4">
        <div className="card">
          <div className="card-body">
            <h2
              className="card-title"
              style={{ textAlign: "center", fontWeight: "normal" }}
            >
              Reservation ID: <strong>{reservationId}</strong>
            </h2>
            {reservation.client && (
              <div className="row" style={{ marginBottom: "1rem" }}>
                <div className="col-md-6">
                  <p className="card-text">
                    Client:{" "}
                    <strong>
                      {reservation.client.clientName +
                        " " +
                        reservation.client.clientSurname}
                    </strong>
                  </p>
                </div>
                <div className="col-md-6">
                  <p className="card-text">
                    Email: <strong>{reservation.client.clientEmail}</strong>
                  </p>
                </div>
              </div>
            )}
            {reservation.busRouteInfo && (
              <>
                <div className="row" style={{ marginBottom: "1rem" }}>
                  <div className="col-md-6">
                    <p className="card-text">
                      Departure City:{" "}
                      <strong>{reservation.busRouteInfo.departureCity}</strong>
                    </p>
                  </div>
                  <div className="col-md-6">
                    <p className="card-text">
                      Arrival City:{" "}
                      <strong>{reservation.busRouteInfo.arrivalCity}</strong>
                    </p>
                  </div>
                </div>
                <div className="row" style={{ marginBottom: "1rem" }}>
                  <div className="col-md-6">
                    <p className="card-text">
                      Departure Time:{" "}
                      <strong>
                        {reservation.busRouteInfo.departureTime
                          ? new Date(
                              reservation.busRouteInfo.departureTime
                            ).toLocaleTimeString([], {
                              day: "2-digit",
                              month: "2-digit",
                              year: "numeric",
                              hour: "2-digit",
                              minute: "2-digit",
                            })
                          : ""}
                      </strong>
                    </p>
                  </div>
                  <div className="col-md-6">
                    <p className="card-text">
                      Arrival Time:{" "}
                      <strong>
                        {reservation.busRouteInfo.arrivalTime
                          ? new Date(
                              reservation.busRouteInfo.arrivalTime
                            ).toLocaleTimeString([], {
                              day: "2-digit",
                              month: "2-digit",
                              year: "numeric",
                              hour: "2-digit",
                              minute: "2-digit",
                            })
                          : ""}
                      </strong>
                    </p>
                  </div>
                </div>
              </>
            )}
            <div className="row" style={{ marginBottom: "1rem" }}>
              <div className="col-md-6">
                <p className="card-text">
                  Reservation Date:{" "}
                  <strong>
                    {reservation.reservationDate
                      ? new Date(
                          reservation.reservationDate
                        ).toLocaleDateString()
                      : ""}
                  </strong>
                </p>
              </div>
              <div className="col-md-6">
                <p className="card-text">
                  Price:{" "}
                  <strong>
                    {price} {currencySymbol}
                  </strong>
                </p>
              </div>
            </div>
            <p className="card-text">
              Reservation Status:{" "}
              <strong
                style={{
                  color:
                    reservation.reverStatus === "Confirmed"
                      ? "green"
                      : reservation.reverStatus === "Canceled"
                      ? "red"
                      : reservation.reverStatus === "Pending"
                      ? "yellow"
                      : "inherit",
                }}
              >
                {reservation.reverStatus}
              </strong>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BookingDetailsComponent;
