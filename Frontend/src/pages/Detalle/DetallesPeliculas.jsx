
import { useEffect, useState } from "react";
import '../Detalle/DetallesPeliculas.css';

const ApiPeliculas = () => {
  const [info, setInfo] = useState(null);
  const [actores, setActores] = useState([]);
  const [anoEstreno, setAnoEstreno] = useState(null);
  const [favorito, setFavorito] = useState(false);

  const toggleFavorito = () => {
    setFavorito(!favorito);
    console.log('agregaste a favoritos')
  };

  useEffect(() => {
    const obtenerInfo = async () => {
      try {
        const response = await fetch("https://api.themoviedb.org/3/movie/507?/language=es-ES&page=1&api_key=7f47a04aea07f5a9052e22b90e30f835");
        const data = await response.json();

        const infoData = {
          title: data.title,
          id: data.id,
          genero: data.genres[0].name,
          popularity: data.popularity,
          overview: data.overview,
          poster_path: data.poster_path,
          video: `https://api.themoviedb.org/3/movie/${data.id}/videos?api_key=7f47a04aea07f5a9052e22b90e30f835`,
        };

        setInfo(infoData);

        const creditsResponse = await fetch(`https://api.themoviedb.org/3/movie/${data.id}/credits?language=es-ES&api_key=7f47a04aea07f5a9052e22b90e30f835`);
        const creditsData = await creditsResponse.json();

        const actoresData = creditsData.cast.slice(0, 9).map(actor => ({
          nombre: actor.name,
          foto: actor.profile_path ? `https://image.tmdb.org/t/p/w200${actor.profile_path}` : null,
        }));
    
        setActores(actoresData);

        const anoEstrenoData = new Date(data.release_date).getFullYear();
        setAnoEstreno(anoEstrenoData);
      } catch (error) {
        console.error('Error al obtener datos de la película:', error);
      }
    };

    obtenerInfo();
  }, []);

  useEffect(() => {
    if (info) {
      const obtenerTrailer = async () => {
        try {
          const response = await fetch(info.video);
          const data = await response.json();

          const trailer = data.results.find(video => video.type === 'Trailer');

          if (trailer) {
            const trailerContainer = document.getElementById('trailer-container');
            trailerContainer.innerHTML = '';

            const iframe = document.createElement('iframe');
            iframe.width = '100%';
            iframe.height = '415';
            iframe.src = `https://www.youtube.com/embed/${trailer.key}`;
            iframe.frameBorder = '0';
            iframe.allowFullScreen = true;

            trailerContainer.appendChild(iframe);
          } else {
            console.error('No se encontraron trailers para esta película.');
          }
        } catch (error) {
          console.error('Error al obtener datos del trailer:', error);
        }
      };

      obtenerTrailer();
    }
  }, [info]);

  return (
    <div>
      {info ? (
        <div className="Contenedor">
          <div className="info-container">
            <div className="info">
              <div>
              <div className="title-movie"> 
              <h1 className=" font-bold title font-sans">{info.title}</h1>
              <button id="favorito" className={`star-button ${favorito ? 'clicked' : ''}`}onClick={toggleFavorito}>★</button>
                </div>
                <img src={`https://image.tmdb.org/t/p/w500${info.poster_path}`} className="w-full h-auto poster" alt="" />
              </div>
            </div>

            <div className="info-description">
               <h2 className="text-xl sm:text-2xl md:text-3xl  font-bold text-center color">Resumen</h2>
              
              <p className="text-center text-lg sm:text-xl md:text-2xl  ">{info.overview}</p>
              <div className="categorias">
                <li className="text-xl sm:text-2xl   font-bold text-center"> Popularidad: <span className="color">{info.popularity}</span> </li>
                <li className="text-xl sm:text-2xl   font-bold text-center"> Genero: <span className="color">{info.genero} </span></li>
                <li className="text-xl sm:text-2xl   font-bold text-center">Año de estreno: <span className="color">{anoEstreno}</span></li>
              </div>

              <h2 className="text-2xl sm:text-3xl font-bold text-center max-h-full title-trailer">Trailer</h2>
              <div id="trailer-container"></div>
            </div>
          </div>

          <div className="actores">
            <h3 className="text-2xl sm:text-3xl title-actores font-bold text-center color">Actores Principales:</h3>
            <ul className="actores-list">
              {actores.map(actor => (
                <li key={actor.nombre}>
                  {actor.foto && actor.foto !== 'ruta_de_imagen_en_blanco.jpg' ? (
                    <img className="actores-img" src={actor.foto} alt={`${actor.nombre} foto`} />
                  ) : (
                    <img className="actores-img" src="https://via.placeholder.com/220" alt="Imagen en blanco" />
                  )}
                  {actor.nombre}
                </li>
              ))}
            </ul>
          </div>
        </div>
      ) : null}
    </div>
  );
}

export default ApiPeliculas;
