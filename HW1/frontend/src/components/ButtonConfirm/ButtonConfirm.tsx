import "./ButtonConfirm.css";

interface Props {
  text?: string;
  handleClick?: () => void;
}

const ButtonConfirm = ({ text, handleClick }: Props) => {
  return (
    <a className="btn btn-primary book-btn" onClick={handleClick}>
      {text}
    </a>
  );
};

export default ButtonConfirm;
