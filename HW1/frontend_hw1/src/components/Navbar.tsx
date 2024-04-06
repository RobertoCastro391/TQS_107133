import ButtonGroup from "react-bootstrap/ButtonGroup";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useCurrency } from '../CurrencyContext';

interface Currency {
  code: string;
  name: string;
  symbol: string;
}

const Navbar = () => {
  const navigate = useNavigate();

  const [currencies, setCurrencies] = useState<Currency[]>([]);
  const { currency, updateCurrency } = useCurrency();
  const [currencySelected, setCurrencySelected] = useState(`${currency.symbol} - ${currency.code} - ${currency.name}`);
  useEffect(() => {
    const fetchCurrencies = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/currency/getAllCurrencies");
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
  
        const currencyArray = Object.keys(data.data).map(key => {
          return {
            code: key,
            name: data.data[key].name,
            symbol: data.data[key].symbol
          };
        });
        
        setCurrencies(currencyArray);
      } catch (error) {
        console.error("There was a problem with fetching the currency list:", error);
      }
    };
  
    fetchCurrencies();
  }, []);
  
  const handleSelectCurrency = async (currency: Currency) => {
    setCurrencySelected(`${currency.symbol} - ${currency.code} - ${currency.name}`);


    try {
      const url = `http://localhost:8080/api/currency/getExhangeRate/${currency.code}`;
      
      const response = await fetch(url , {
        method: "GET",
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const data = await response.json();
      updateCurrency({
        code: currency.code,
        name: currency.name,
        symbol: currency.symbol,
        exchangeRate: {
          currencyCode: data.data[currency.code]
        }
      });
    } catch (error) {
      console.error("There was a problem with fetching the exchange rate:", error);
    }
  };

  const handleSearchClick = () => {
    navigate("/search"); 
  };

  const handleHomeClick = () => {
    navigate("/");
  };

  return (
    <nav
      className="navbar navbar-expand-lg bg-body-tertiary"
      data-bs-theme="dark"
    >
      <div className="container-fluid">
        <a className="navbar-brand" onClick={() => handleHomeClick()}>
          BusTickets - TQS
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNavDropdown">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a
                className="nav-link active"
                aria-current="page"
                onClick={handleSearchClick}
              >
                Search Bus Trips
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link active"
                aria-current="page"
                href="/checkBooking"
              >
                Check your booking
              </a>
            </li>
          </ul>

          <div className="ms-auto">
            <DropdownButton
              as={ButtonGroup}
              key="Currency"
              id={`dropdown-variants-Currency`}
              variant="danger"
              title={currencySelected}
            >
              <div style={{ maxHeight: "500px", overflowY: "auto" }}>
                {currencies.map((currency, index) => (
                  <Dropdown.Item
                    key={index}
                    eventKey={index}
                    onClick={() => handleSelectCurrency(currency)}
                  >
                    {currency.symbol} - {currency.code} - {currency.name}
                  </Dropdown.Item>
                ))}
              </div>
            </DropdownButton>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
