import{ useState } from "react";
import { useNavigate } from "react-router-dom";
import "./SearchComoponent2.css";

interface props {
  from?: string;
  to?: string;
  date?: string;
}

const SeachComponent2 = ( {from, to, date} : props) => {
  
  const [departureCity, setDepartureCity] = useState(from);
  const [arrivalCity, setArrivalCity] = useState(to);
  const [departureDate, setDepartureDate] = useState(date);

  const navigate = useNavigate();

  const handleSearch = () => {
    navigate('/list', {
      state: {
        from: departureCity,
        to: arrivalCity,
        date: departureDate,
      },
    });
  };

  return (
    <div className="container-style">
      <div className="container2-style">
        <div className="input-outside-style">
          <p>Departure City:</p>
          <input
            type="text"
            className="input-inside-style"
            value={departureCity}
            onChange={(e) => setDepartureCity(e.target.value)}
          />
        </div>
        <div className="input-outside-style">
          <p>Arrival City:</p>
          <input
            type="text"
            className="input-inside-style"
            value={arrivalCity}
            onChange={(e) => setArrivalCity(e.target.value)}
          />
        </div>
        <div className="input-outside-style">
          <p>Departure Date:</p>
          <input
            type="date"
            className="input-inside-style"
            value={departureDate}
            onChange={(e) => setDepartureDate(e.target.value)}
          />
        </div>
      </div>
      <div className="container3-style">
        <button className="button-style" onClick={handleSearch}>Search</button>
      </div>
    </div>
  );
};

export default SeachComponent2;

