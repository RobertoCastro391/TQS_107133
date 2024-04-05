import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./PassengerDetailsForm.css";
import ButtonConfirm from "../ButtonConfirm/ButtonConfirm";
import { useCurrency } from "../../CurrencyContext";

interface Props {
  busNumber?: string;
  fare: number;
  departureTime?: string;
}

const PassengerDetailsForm = ({ busNumber, fare, departureTime }: Props) => {
  const navigate = useNavigate();
  const { currency } = useCurrency();

  const exchangeRate = currency.exchangeRate.currencyCode;
  const currencySymbol = currency.symbol;
  const price = (fare * exchangeRate).toFixed(2);

  const [clientName, setClientName] = useState("");
  const [clientSurname, setClientSurname] = useState("");
  const [clientEmail, setClientEmail] = useState("");
  const [clientAddress, setClientAddress] = useState("");
  const [clientPostalCode, setClientPostalCode] = useState("");
  const [clientCity, setClientCity] = useState("");
  const [clientCountry, setClientCountry] = useState("");
  const [clientPhone, setClientPhone] = useState("");
  const [creditCardNumber, setCreditCardNumber] = useState("");
  const [creditCardExpiration, setCreditCardExpiration] = useState("");
  const [creditCardCVV, setCreditCardCVV] = useState("");

  const handleBook = async () => {
    const fields = [
      clientName,
      clientSurname,
      clientEmail,
      clientAddress,
      clientPostalCode,
      clientCity,
      clientCountry,
      clientPhone,
      creditCardNumber,
      creditCardExpiration,
      creditCardCVV,
    ];

    const isAnyFieldEmpty = fields.some((field) => field.trim() === "");

    if (isAnyFieldEmpty) {
      alert("Please fill all the fields");
      return;
    }

    const passengerDetails = {
      routeId: busNumber,
      clientName: clientName,
      clientSurname: clientSurname,
      clientEmail: clientEmail,
      clientAddress: clientAddress,
      clientPostalCode: clientPostalCode,
      clientCity: clientCity,
      clientCountry: clientCountry,
      clientPhone: clientPhone,
      reservationDate: new Date().toISOString().slice(0, 10),
      creditCardNumber: creditCardNumber,
      creditCardExpiration: creditCardExpiration,
      creditCardCVV: creditCardCVV,
      price: fare,
    };

    const endpoint = "http://localhost:8080/api/reservation/createReservation";

    try {
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(passengerDetails),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      console.log("Success:", data);

      navigate("/bookingConfirmation", {
        state: { data, clientName, clientSurname },
      });
    } catch (error) {
      console.error("Error during booking:", error);
    }
  };

  return (
    <div className="container-passenger-details-form">
      <p className="label1">
        Insert Passenger Details - <strong>{busNumber}</strong> -{" "}
        <strong>{departureTime}</strong>
      </p>
      <div className="container-inside-passenger-details-form">
        <div className="column">
          <label>Name</label>
          <input
            type="text"
            value={clientName}
            onChange={(e) => setClientName(e.target.value)}
            required
          />
          <label>Surname</label>
          <input
            type="text"
            value={clientSurname}
            onChange={(e) => setClientSurname(e.target.value)}
            required
          />
          <label>Email</label>
          <input
            type="email"
            value={clientEmail}
            onChange={(e) => setClientEmail(e.target.value)}
            required
          />
          <label>Address</label>
          <input
            type="text"
            value={clientAddress}
            onChange={(e) => setClientAddress(e.target.value)}
            required
          />
        </div>
        <div className="column">
          <label>Postal Code</label>
          <input
            type="text"
            value={clientPostalCode}
            onChange={(e) => setClientPostalCode(e.target.value)}
            required
          />
          <label>City</label>
          <input
            type="text"
            value={clientCity}
            onChange={(e) => setClientCity(e.target.value)}
            required
          />
          <label>Country</label>
          <input
            type="text"
            value={clientCountry}
            onChange={(e) => setClientCountry(e.target.value)}
            required
          />
          <label>Phone Number</label>
          <input
            type="text"
            value={clientPhone}
            onChange={(e) => setClientPhone(e.target.value)}
            required
          />
        </div>
      </div>
      <p className="label2">
        Insert Credit Card Details - <strong>{price} {currencySymbol}</strong>
      </p>
      <div className="container-inside-passenger-details-form">
        <div className="column">
          <label>Credit Card Number</label>
          <input
            type="text"
            value={creditCardNumber}
            onChange={(e) => setCreditCardNumber(e.target.value)}
            required
          />
          <label>Expiration Date - (MM/YYYY)</label>
          <input
            type="text"
            value={creditCardExpiration}
            onChange={(e) => setCreditCardExpiration(e.target.value)}
            required
          />
        </div>
        <div className="column">
          <label>CVV</label>
          <input
            type="text"
            value={creditCardCVV}
            onChange={(e) => setCreditCardCVV(e.target.value)}
            required
          />
        </div>
      </div>
      <ButtonConfirm text="Confirm Booking !" handleClick={handleBook} />
    </div>
  );
};

export default PassengerDetailsForm;
