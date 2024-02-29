import { useState, useEffect } from "react";
import axios from "axios";
import Card from "../Card/Card";
import.meta.env.VITE_API_KEY;
import Pagination from "../Pagination/Pagination";


const apiKey = import.meta.env.VITE_API_KEY;
console.log(import.meta.env.VITE_API_KEY);

const CardContainer = () => {
  // estado para actualizar peliculas
  const [movies, setMovies] = useState([]);

  //estado para paginacion
  const [page, setPage] = useState(1);
  const [totalMovies, setTotalMovies] = useState(0);


  // imagen de portada
  // const [coverMovies, setCoverMovies]
  useEffect(() => {
    const moviesData = async () => {
      try {
        const response = await axios(
          `https://api.themoviedb.org/3/discover/movie?api_key=${apiKey}&page=${page}`
        );

        if (response.status !== 200) {
          throw new Error("Network response was not ok");
        }

        const data = response.data;
        setMovies(data.results);

        
        const maxPages = 100;
        setTotalMovies(Math.min(maxPages, Math.ceil(response.data.total_results / 10)));
        
      } catch (error) {
        console.error("Error axios movies:", error);
      }
    };

    moviesData();
  }, [page]);

  // añado las secciones que va a tener: top ranking y populares

  const popularMovies = [...movies]
    .sort((a, b) => b.popularity - a.popularity)
    .slice(0, 10);

  return (
    <div className="mx-auto sm:text-center sm:w-full md:w-2/3  xl:w-1/3 2xl:w-3/4">
      <div className="mb-4">
      <h2 className="text-center text-lg font-bold mb-2">Populares</h2>
      <div className="grid grid-cols-1 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {popularMovies.map((movie) => (
          <Card key={movie.id} movie={movie} />
        ))}
      </div>
    </div>

    <div className="mb-4">
      <h2 className="text-center text-lg font-bold mb-2">Movies</h2>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {movies.map((movie) => (
          <Card key={movie.id} movie={movie} />
        ))}
      </div>
    </div>
      

      <div>
        <Pagination currentPage={page} itemsPerPage={10} totalMovies={totalMovies} onPageChange={setPage} />
      

      </div>

    </div>
  );
};

export default CardContainer;
