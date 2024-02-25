import { Link } from 'react-router-dom';

const PaginaNoEncontrada = () => {
  return (
    <div className="text-center mt-56">
      <h1 className="text-4xl text-red-500 font-bold max-h-3 bg-midnight text-tahiti">
        La Página Solicitada No se encuentra o no existe.
      </h1>
      <Link to="/login" className="text-4xl mt-56  font-bold max-h-3 bg-midnight text-tahiti block ">
        Volver al Inicio
      </Link>
    </div>
  );
};

export default PaginaNoEncontrada;