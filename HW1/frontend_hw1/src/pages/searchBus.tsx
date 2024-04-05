import Footer from "../components/Footer/Footer";
import Navbar from "../components/Navbar";

import "bootstrap/dist/css/bootstrap.min.css";
import SearchComponent from "../components/SearchComponent";

const SearchBus = () => {
  const backgroundImageUrl =
    "https://www.cm-gondomar.pt/wp-content/uploads/2021/07/210807_Novos_Autocarros_Inclusivos_Mobilidade_Reduzida-2-scaled.jpg";
  return (
    <>
      <Navbar />
      <div style={{
        backgroundImage: `url(${backgroundImageUrl})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        minHeight: "calc(100vh - 56px)",
      }}>
        <SearchComponent />
      </div>
      <Footer />
    </>
  );
};

export default SearchBus;
