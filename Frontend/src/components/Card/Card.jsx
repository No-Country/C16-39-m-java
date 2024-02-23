/* eslint-disable react/prop-types */


const Card = ({movie}) =>{

const {title, vote_average, poster_path} = movie;
const imageUrl= `https://image.tmdb.org/t/p/w500${poster_path}`;

    return(
        <div>

        <img src={imageUrl} alt={title} />
        <h3>{title}</h3>
        <p>Ranking:{vote_average}</p>


        </div>
    )
}

export default Card;