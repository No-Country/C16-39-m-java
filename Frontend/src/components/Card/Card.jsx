/* eslint-disable react/prop-types */


const Card = ({movie}) =>{

const {title, vote_average, poster_path} = movie;
const imageUrl= `https://image.tmdb.org/t/p/w500${poster_path}`;

const rank= Math.floor(vote_average);
    return(
        <div className="flex-row m-4 text-center ">

        <div className="relative mx-auto max-w-xs  md:max-w-full lg:max-w-full xl:max-w-full">

        <img className=" w-full h-auto sm:mx-auto rounded-2xl " src={imageUrl} alt={title} />
        
        <p className="absolute top-0 right-2 bg-black bg-opacity-50 text-white rounded-md">⭐{rank}</p>


        </div>

        </div>
    )
}

export default Card;