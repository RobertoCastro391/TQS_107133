import { useState } from "react";
import { useNavigate } from "react-router-dom";

const SearchComponent = () => {
  const navigate = useNavigate();

  const [from, setFrom] = useState("");
  const [to, setTo] = useState("");
  const [date, setDate] = useState("");

  const handleSearch = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("searching...", { from, to, date });
    navigate("/list", { state: { from, to, date } });
  };

  return (
    <div className="container mt-4">
      <h1
        className="mb-4 text-center"
        style={{ color: "white", fontSize: "3rem", fontWeight: "bold" }}
      >
        Search and Find the best options of bus trips here!
      </h1>
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow">
            <div className="card-body">
              <p
                className="text-muted text-center"
                style={{ fontSize: "2rem", fontWeight: "bold" }}
              >
                Search for bus trips below
              </p>
              <form onSubmit={handleSearch}>
                <div className="mb-3">
                  <label htmlFor="from" className="form-label">
                    From:
                  </label>
                  <input
                    type="text"
                    id="from"
                    name="from"
                    value={from}
                    onChange={(e) => setFrom(e.target.value)}
                    className="form-control"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="to" className="form-label">
                    To:
                  </label>
                  <input
                    type="text"
                    id="to"
                    name="to"
                    value={to}
                    onChange={(e) => setTo(e.target.value)}
                    className="form-control"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="date" className="form-label">
                    Date:
                  </label>
                  <input
                    type="date"
                    id="date"
                    name="date"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    className="form-control"
                    required
                  />
                </div>
                <div className="text-center">
                  {/* Remova o evento onClick do botão e deixe o formulário lidar com a submissão */}
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

export default SearchComponent;
