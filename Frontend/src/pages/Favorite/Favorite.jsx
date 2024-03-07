import axios from 'axios'
import React, { useContext, useState } from 'react'
import { useEffect } from 'react'
import { AuthContext } from '../../context/AuthContext/AuthContext'
import { MdDelete } from "react-icons/md";

const Favorite = () => {

    const URL_BACKEND = 'https://movies-apirest-c77e9f5e2ba2.herokuapp.com'

    const [favoriteMovies, setFavoriteMovies] = useState([])
    const [message, setMessage] = useState('')
    const [loading, setLoading] = useState(true)

    const { userData } = useContext(AuthContext)
    const token = userData.token

    useEffect(() => {
        axios.get(`${URL_BACKEND}/users/favorites`, { headers: { 'Authorization': `Bearer ${token}` } })
            .then(response => {
                setFavoriteMovies(response.data.content)
            })
            .catch((error) => {
                const {statusCode} =  error.response.data
                statusCode === 404 ? setMessage('No tienes peliculas favoritas') : setMessage('Hubo un problema en el servidor')
            })
            .finally(() => setLoading(false))
    }, [])

    if (loading) {
        return (
            <div className='h-screen flex justify-center items-center'>
                <span className="loading loading-spinner loading-lg"></span>
            </div>)
    }

    const deleteMovie = (movieId) => {
        axios.delete(`${URL_BACKEND}/users/favorites/${movieId}`, { headers: { 'Authorization': `Bearer ${token}` } })
            .then(() => {
                setFavoriteMovies(prevMovies => prevMovies.filter(movie => movie.id !== movieId))
                localStorage.setItem(`favoriteStatus-${movieId}`, 'false')
            })
            .catch(error => {
                console.error('Error al eliminar la película:', error)
            })
    }

    return (
        <section className='px-4 text-white py-6 pb-20 lg:ml-48'>
            <h2 className=' text-xl font-bold pb-8'>Películas Favoritas</h2>
            <p className=' text-center'>{message}</p>
            <div className=' flex justify-around items-center flex-wrap gap-4'>
                {favoriteMovies.map(movie => (
                    <figure key={movie.id} className='relative space-y-2 mb-3'>
                        <img className='w-36 rounded-2xl lg:w-44 opacity-75' src={`https://image.tmdb.org/t/p/w500${movie.posterPath}`} alt={movie.title} />
                        <figcaption className=' text-sm text-center m-auto w-32 lg:w-40'>{movie.title}</figcaption>
                        <button onClick={() => deleteMovie(movie.id)} className='absolute top-1 right-3 text-red-500 text-2xl'><MdDelete /></button>
                    </figure>
                ))}
            </div>
        </section>
    )
}

export default Favorite