import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom'
import axios from 'axios'
import { FaStar } from "react-icons/fa";
import { MdFavoriteBorder } from "react-icons/md";
import { MdError } from "react-icons/md";

const DetallesPeliculas = () => {

    const URL = 'https://api.themoviedb.org/3/movie'
    const API_KEY = import.meta.env.VITE_API_KEY

    const [movieId, setMovieId] = useState([])
    const [actors, setActors] = useState([])
    const [staff, setStaff] = useState([])
    const [trailerKey, setTrailerKey] = useState('')
    const [errorMessage, setErrorMessage] = useState(false)

    const { id } = useParams()

    useEffect(() => {

        const fetchData = async () => {
            try {
                const [movieResponse, creditsResponse, videosResponse] = await Promise.all([
                    axios.get(`${URL}/${id}?language=es-ES&api_key=${API_KEY}`),
                    axios.get(`${URL}/${id}/credits?language=es-ES&api_key=${API_KEY}`),
                    axios.get(`${URL}/${id}/videos?language=es-ES&api_key=${API_KEY}`)
                ])

                setMovieId(movieResponse.data)
                setActors(creditsResponse.data.cast)
                setStaff(creditsResponse.data.crew)
                setTrailerKey(videosResponse.data.results[0].key)

            } catch (error) {
                console.log(error)
                setErrorMessage(true)
            }
        }

        fetchData()

    }, [id])

    if (errorMessage) {
        return (
            <div className='h-screen flex flex-col items-center justify-center space-y-3'>
                <MdError className=' text-red-500 text-4xl' />
                <p className=' text-red-500 text-sm text-center md:text-base'>Hubo un problema en el servidor.<br />Por favor, inténtalo de nuevo más tarde.</p>
            </div>
        )
    }

    const director = staff.find(member => member.job === 'Director')

    return (
        <section className="px-4 text-white pt-4 pb-20 lg:ml-48 xl:px-12 lg:pt-8">
            <div className=" flex justify-center">
                <iframe
                    className="rounded-lg w-full h-72 lg:rounded-2xl xl:h-96"
                    src={`https://www.youtube.com/embed/${trailerKey}`}
                    allowFullScreen
                ></iframe>
            </div>
            <div className="pt-6 space-y-6">
                <div className=" flex flex-wrap items-center justify-between">
                    <div>
                        <p className="text-lg font-medium xl:text-xl">{movieId.title}</p>
                        <p className="text-xs">{movieId.runtime} min</p>
                        {movieId.genres && movieId.genres.map(genre => (
                            <p key={genre.id} className="badge badge-outline text-xs text-stone-400 my-3 mr-1">{genre.name}</p>
                        ))}
                    </div>
                    <div className=" flex items-center font-medium">
                        <MdFavoriteBorder className=" text-[1.4rem] mr-2 text-stone-400" />
                        <FaStar className=" text-yellow-400 mr-1" />
                        <p className=" mr-1">{movieId.vote_average}</p>
                        <p className=" text-xs text-stone-400">| {movieId.popularity}k</p>
                    </div>
                </div>
                <div className=" text-sm text-stone-200 xl:text-base">
                    <p>{movieId.overview}</p>
                </div>
                <div className=" text-sm font-medium xl:text-base">
                    <p className=" ">Director: {director && <span className="text-yellow-400">{director.name}</span>}</p>
                    <p className="mt-2">Actores Principales:</p>
                    {actors.slice(0, 6).map(actor => (
                        <p className="text-yellow-400 my-1" key={actor.id}>{actor.name}</p>))}
                </div>
            </div>
        </section>
    )
}

export default DetallesPeliculas