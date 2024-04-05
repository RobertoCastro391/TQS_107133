
import { Routes, Route } from 'react-router-dom';
import SearchComponent from './pages/searchBus';
import ListBus from './pages/ListBus';
import PassengerDetails from './pages/PassengerDetails';
import ConfirmBooking from './pages/ConfirmBooking';
import CheckBooking from './pages/CheckBooking';
import BookingDetails from './pages/BookingDetails';


const App = () => {
  return (
    <Routes>
      <Route path="/" element={<SearchComponent />} /> 
      <Route path="/search" element={<SearchComponent />} />
      <Route path="/list" element={<ListBus />} />
      <Route path="/passenger-details" element={<PassengerDetails />} />
      <Route path="/bookingConfirmation" element={<ConfirmBooking />} />
      <Route path="/checkBooking" element={<CheckBooking />} />
      <Route path="/bookingDetails" element={<BookingDetails />} />
      {/* <Route path="*" element={<h1>Upss...</h1>} />  */}
      {/* ... other routes */}
    </Routes>
  );
};

export default App;