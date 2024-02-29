import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom'
import axios from 'axios'
import { FaStar } from "react-icons/fa";
import { MdFavoriteBorder } from "react-icons/md";

const DetallesPeliculas = () => {

    const URL = 'https://api.themoviedb.org/3/movie'
    const API_KEY = import.meta.env.VITE_API_KEY

    const [movie, setMovie] = useState([])
    const [actors, setActors] = useState([])
    const [staff, setStaff] = useState([])

    const { id } = useParams()

    useEffect(() => {
        axios.get(`${URL}/${id}?api_key=${API_KEY}`)
            .then(responde => {
                setMovie(responde.data)
            })
            .catch(error => {
                console.log(error)
            })

        axios.get(`${URL}/${id}/credits?api_key=${API_KEY}`)
            .then(responde => {
                setActors(responde.data.cast)
                setStaff(responde.data.crew)
            })
            .catch(error => {
                console.log(error)
            })
    }, [id])

    const director = staff.find(member => member.job === 'Director')

    return (
        <section className="px-4 text-white pt-4 pb-20 lg:ml-48 xl:px-12">
            <div className=" flex justify-center">
                <img className="rounded-xl w-full md:w-[28rem] lg:w-[32rem]" src={`https://image.tmdb.org/t/p/w500${movie.backdrop_path}`} alt={`${movie.title}`} title={`${movie.title}`}/>
            </div>
            <div className="pt-6 space-y-6">
                <div className=" flex flex-wrap items-center justify-between space-x-3">
                    <p>{movie.title}</p>
                    <p className=" hidden">{movie.runtime} min</p>
                    <div className=" flex items-center font-medium">
                        <MdFavoriteBorder className=" text-[1.4rem] mr-2 text-stone-400" />
                        <FaStar className=" text-yellow-400 mr-1" />
                        <p className=" mr-1 ">{movie.vote_average}</p>
                        <p className=" text-xs text-stone-400">| {movie.popularity} k</p>
                    </div>
                </div>
                <div>
                    <p className=" text-sm text-stone-200">{movie.overview}</p>
                </div>
                <div className=" text-sm font-medium">
                    <p className=" ">Director: {director && <span className="text-yellow-400">{director.name}</span>}</p>
                    <p className="mt-2">Actores Principales:</p>
                    {actors.slice(0, 6).map(actor => (
                    <p className="text-yellow-400 my-1" key={actor.id}>{actor.name}</p>))}
                </div>
            </div>
        </section>
    );
}

export default DetallesPeliculas;
