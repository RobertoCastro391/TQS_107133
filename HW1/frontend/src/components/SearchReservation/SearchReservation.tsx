import { useState } from "react";
import { useNavigate } from "react-router-dom";

const SearchReservation = () => {
  const navigate = useNavigate();

  const [reservationId, setReservationId] = useState("");

  const handleSearch = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("searching...", { reservationId });
    navigate("/bookingDetails", { state: { reservationId } });
  };

  return (
    <div className="container mt-4">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow">
            <div className="card-body">
              <p
                className="text-muted text-center"
                style={{ fontSize: "2rem", fontWeight: "bold" }}
              >
                Check your booking details here!
              </p>
              <form onSubmit={handleSearch}>
                <div className="mb-3">
                  <label htmlFor="from" className="form-label">
                    Reservation Id:
                  </label>
                  <input
                    type="text"
                    id="from"
                    name="from"
                    value={reservationId}
                    onChange={(e) => setReservationId(e.target.value)}
                    className="form-control"
                    required
                  />
                </div>
                <div className="text-center">
                  <button type="submit" className="btn btn-primary">
                    Search
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchReservation;