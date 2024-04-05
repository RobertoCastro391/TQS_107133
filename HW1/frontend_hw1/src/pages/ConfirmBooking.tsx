import { useLocation } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer/Footer";

const ConfirmBooking = () => {
    const location = useLocation();
    const { data, clientName, clientSurname } = location.state || {};

  return (
    <>
      <Navbar />
        <div style={{ padding: '2%' }} >
            <h1 style={{ textAlign: 'center', fontWeight: 'bold' }}>Booking Confirmed!!</h1>
            <p>Congratulations, <strong>{clientName} {clientSurname}</strong> your bus ticket is confirmed! You can check your reservation details on the <a href="/checkBooking">Check My Booking</a> page!</p>
            <p>Your booking id is: <strong>{data.ticketId}</strong>!</p>
        </div>
      <Footer />
    </>
  );
};

export default ConfirmBooking;