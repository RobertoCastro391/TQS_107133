import Navbar from "../components/Navbar";
import Footer from "../components/Footer/Footer";
import PassengerDetailsForm from "../components/PassengerDetailsForm/PassengerDetailsForm";
import { useLocation } from "react-router-dom";

const PassengerDetails = () => {

  const location = useLocation();
  const busNumber = location.state.busNumber || {};
  const fare = location.state.fare || {};
  let departureTime = location.state.departureTime || {};
  departureTime = new Date(departureTime).toLocaleDateString([], {
    day: "2-digit",
    month: "2-digit",
    year: "numeric"
  });

  return (
    <>
      <Navbar />
      <PassengerDetailsForm busNumber={busNumber} fare={fare} departureTime={departureTime} />
      <Footer />
    </>
  );
};

export default PassengerDetails;