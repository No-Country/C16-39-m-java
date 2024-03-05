/* eslint-disable react/prop-types */

import { Link } from "react-router-dom";


const Card = ({movie}) =>{

const {title, vote_average, poster_path, id} = movie;
const imageUrl= `https://image.tmdb.org/t/p/w500${poster_path}`;

const rank= Math.floor(vote_average);
    return(
        
        <Link to={`/Detalle/${id}`} className="flex flex-col items-center justify-center m-1"> 
                       {/* flex-row sm:flex-row m-2 text-center //flex-col sm:flex-row m-2 text-center items-center*/}

        <div className="relative mx-auto max-w-xs md:max-w-full lg:max-w-full xl:max-w-full">

        <img className="w-full h-auto sm:mx-auto rounded-2xl " src={imageUrl} alt={title} />
        
        <p className="absolute top-0 right-2 bg-black bg-opacity-50 text-white rounded-md">⭐{rank}</p>


        </div>

        </Link>
    )
}

export default Card;