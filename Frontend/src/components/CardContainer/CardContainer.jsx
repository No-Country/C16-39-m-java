import { useState, useEffect } from "react";
import axios from "axios";

import Card from "../Card/Card"

const CardContainer = () => {
  const [movies, setMovies] = useState([]);

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
  },[]);
  return (

    <div>
      {movies.map ((movie)=>(
        <Card key={movie.id} movie={movie}/>
      ))}
        
    </div>

  ) 
};

export default CardContainer;
