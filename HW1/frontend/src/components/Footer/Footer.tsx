import './Footer.css';

const Footer = () => {
  return (
    <footer className="site-footer">
      <div className="footer-content">
        <div className="footer-section">
          <h5>About Us</h5>
          <p>Web Aplication for the HW1: Mid-term assigment</p>
          <p>Teste e Qualidade de Serviço - TQS (2023/2024)</p>
        </div>
        <div className="footer-section">
          <h5>Work made by:</h5>
          <p>Roberto Rolão de Castro | 107133</p>
          <p>Email: robertorcastro@ua.pt</p>
        </div>
      </div>
      <div className="footer-bottom">
        &copy; 2024 Roberto Rolão de Castro
      </div>
    </footer>
  );
};

export default Footer;
