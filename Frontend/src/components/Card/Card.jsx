/* eslint-disable react/prop-types */


const Card = ({movie}) =>{

const {title, vote_average, poster_path} = movie;
const imageUrl= `https://image.tmdb.org/t/p/w500${poster_path}`;

const rank= Math.floor(vote_average);
    return(
        <div className="flex-row m-4 sm:w-full, md:w-1/2, lg:w-1/3, xl:w-1/4, y 2xl:w-1/5">

        <div className="relative">

        <img className="w-44 h-auto rounded-2xl  " src={imageUrl} alt={title} />
        
        <p className="absolute top-0 right-2 bg-black bg-opacity-50 text-white rounded-md">⭐{rank}</p>


        </div>

        </div>
    )
}

export default Card;