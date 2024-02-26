import { useState, useEffect } from "react";
import axios from "axios";

import Card from "../Card/Card";

const CardContainer = () => {
  // estado para actualizar peliculas
  const [movies, setMovies] = useState([]);
  // imagen de portada
 // const [coverMovies, setCoverMovies]

  useEffect(() => {
    const moviesData = async () => {
      try {
        const response = await axios(
          "https://api.themoviedb.org/3/discover/movie?api_key=49fc98d76d87c204ce53823aeab3ef62"
        );

        if (response.status !== 200) {
          throw new Error("Network response was not ok");
        }

        const data = response.data;
        setMovies(data.results);
      } catch (error) {
        console.error("Error axios movies:", error);
      }
    };
    moviesData();
  }, []);

  // añodo las secciones que va a tener: top ranking y populares

  const popularMovies = [...movies]
    .sort((a, b) => b.popularity - a.popularity)
    .slice(0, 4);
  const topRanking = [...movies]
    .sort((a, b) => b.vote_average - a.vote_average)
    .slice(0, 4);


    console.log('Películas populares:', popularMovies);
    console.log('Películas mejor calificadas:', topRanking);
  return (
    <div>
        <h2 className="ml-6 text-lg font-bold">Populares</h2>
      <div className="flex flex-wrap space-x-6">
        {popularMovies.map((movie) => (
          <Card key={movie.id} movie={movie} />
        ))}
      </div>


        <h2 className="ml-6 text-lg font-bold">Top Ranking</h2>
      <div className="flex flex-wrap space-x-6">
        {topRanking.map((movie) => (
          <Card key={movie.id} movie={movie} />
        ))}
      </div>
    </div>
  );
};

export default CardContainer;
