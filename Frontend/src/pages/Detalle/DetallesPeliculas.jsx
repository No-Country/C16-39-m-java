import { useContext, useEffect, useState } from "react";
import { useParams } from 'react-router-dom'
import axios from 'axios'
import { FaStar } from "react-icons/fa";
import { MdFavorite } from "react-icons/md";
import { MdFavoriteBorder } from "react-icons/md";
import { MdError } from "react-icons/md";
import { AuthContext } from "../../context/AuthContext/AuthContext";

const DetallesPeliculas = () => {

    const URL = 'https://api.themoviedb.org/3/movie'
    const API_KEY = import.meta.env.VITE_API_KEY
    const URL_BACKEND = 'https://movies-apirest-c77e9f5e2ba2.herokuapp.com'

    const [movieId, setMovieId] = useState([])
    const [actors, setActors] = useState([])
    const [staff, setStaff] = useState([])
    const [trailerKey, setTrailerKey] = useState('')
    const [errorMessage, setErrorMessage] = useState(false)
    const [addFavorite, setAddFavorite] = useState(false)
    const [loading, setLoading] = useState(true)

    const { id } = useParams()

    const { userData } = useContext(AuthContext)

    const token = userData.token

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

                const favoriteStatus = localStorage.getItem(`favoriteStatus-${id}`)
                if (favoriteStatus === 'true') {
                    setAddFavorite(true)
                }

            } catch (error) {
                console.log(error)
                setErrorMessage(true)
            } finally {
                setLoading(false)
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

    if (loading) {
        return (
            <div className="px-4 py-6 h-screen flex flex-col gap-4 lg:ml-48 xl:px-12">
                <div className="skeleton h-72"></div>
                <div className=" flex items-center justify-between mb-7">
                    <div className=" space-y-3">
                        <p className="skeleton h-3 w-28"></p>
                        <p className="skeleton h-2 w-16"></p>
                    </div>
                    <div className="space-y-3">
                        <p className="skeleton h-3 w-28"></p>
                    </div>
                </div>
                <p className="skeleton h-2"></p>
                <p className="skeleton h-2"></p>
                <p className="skeleton h-2"></p>
            </div>
        )
    }

    const director = staff.find(member => member.job === 'Director')

    const handleFavorite = async () => {
        try {
            if (addFavorite) {
                await axios.delete(`${URL_BACKEND}/users/favorites/${id}`, {
                    headers: { 'Authorization': `Bearer ${token}` }
                })
                setAddFavorite(false)
                localStorage.setItem(`favoriteStatus-${id}`, 'false')

            } else {
                const data = {
                    id: movieId.id,
                    title: movieId.title,
                    overview: movieId.overview,
                    releaseYear: movieId.release_date,
                    posterPath: movieId.poster_path,
                    backdropPath: movieId.backdrop_path
                }
                await axios.post(`${URL_BACKEND}/users/favorites`, data, {
                    headers: { 'Authorization': `Bearer ${token}` }
                })

                setAddFavorite(true)
                localStorage.setItem(`favoriteStatus-${id}`, 'true')
            }
        } catch (error) {
            console.error(error)
        }
    }
    
    return (
        <section className="px-4 text-white pt-4 pb-20 lg:ml-48 xl:px-12 lg:pt-8">
            <div className=" flex justify-center">
                <iframe
                    className="skeleton rounded-lg w-full h-72 lg:rounded-2xl xl:h-96"
                    src={`https://www.youtube.com/embed/${trailerKey}`}
                    allowFullScreen>
                </iframe>
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
                        <button onClick={handleFavorite} data-tip="Agregar a Favoritos" className="text-[1.4rem] mr-2 md:tooltip">
                            {addFavorite ? <MdFavorite className='text-red-600' /> : <MdFavoriteBorder className=" text-stone-500" />}
                        </button>
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