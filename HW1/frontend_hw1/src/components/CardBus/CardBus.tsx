import { useNavigate } from "react-router-dom";
import "./CardBus.css";
import ButtonConfirm from "../ButtonConfirm/ButtonConfirm";
import { useCurrency } from "../../CurrencyContext";

interface props {
  companyName: string;
  busNumber: string;
  departureCity: string;
  arrivalCity: string;
  departureTime: string;
  arrivalTime: string;
  fare: number;
}

const CardBus = ({
  companyName,
  busNumber,
  departureCity,
  arrivalCity,
  departureTime,
  arrivalTime,
  fare,
}: props) => {
  const navigate = useNavigate();
  const { currency } = useCurrency();

  const exchangeRate = currency.exchangeRate.currencyCode;
  const currencySymbol = currency.symbol;
  const price = (fare * exchangeRate).toFixed(2);


  const handleBook = () => {
    navigate("/passenger-details", {
      state: { busNumber, fare, departureTime },
    });
  };

  return (
    <div className="bus-card">
      <div className="bus-card-header">
        {companyName}
        <h5>
          Bus Number: <strong>{busNumber}</strong>
        </h5>
      </div>
      <div className="bus-card-body">
        <div style={{ marginLeft: "2%", marginRight: "2%" }}>
          <div className="bus-card-route">
            <div style={{ display: "flex" }}>
              <p className="label">Departure City:</p>
              <p className="label-props">{departureCity}</p>
            </div>
            <div style={{ display: "flex" }}>
              <p className="label">Arrival City:</p>
              <p className="label-props">{arrivalCity}</p>
            </div>
          </div>
          <div className="bus-card-route">
            <div style={{ display: "flex" }}>
              <p className="label">Departure Time:</p>
              <p className="label-props">
                {new Date(departureTime).toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                  hour12: false,
                })}
              </p>
            </div>
            <div style={{ display: "flex" }}>
              <p className="label">Arrival Time:</p>
              <p className="label-props">
                {new Date(arrivalTime).toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                  hour12: false,
                })}
              </p>
            </div>
          </div>
        </div>
        <p className="card-fare">
          {price} {currencySymbol}
        </p>
        <ButtonConfirm text="Book Now !" handleClick={handleBook} />
      </div>
    </div>
  );
};

export default CardBus;
