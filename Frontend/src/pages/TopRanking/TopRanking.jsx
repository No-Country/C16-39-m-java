import React, { useEffect } from 'react';
import axios from 'axios'
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { FaStar } from "react-icons/fa";
import { MdError } from "react-icons/md";

const TopRanking = () => {

    const URL = 'https://api.themoviedb.org/3/movie/top_rated'
    const API_KEY = import.meta.env.VITE_API_KEY

    const [movies, setMovies] = useState([])
    const [page, setPage] = useState(1)
    const [loading, setLoading] = useState(false)
    const [firstPageLoaded, setFirstPageLoaded] = useState(false)
    const [errorMessage, setErrorMessage] = useState(false)

    useEffect(() => {
        if (!firstPageLoaded) {
            setLoading(true)
            axios.get(`${URL}?page=${page}&api_key=${API_KEY}`)
                .then(response => {
                    setMovies(response.data.results)
                    setFirstPageLoaded(true)
                    setLoading(false)
                })
                .catch(error => {
                    console.log(error)
                    setLoading(false)
                    setErrorMessage(true)
                })
        }
    }, [firstPageLoaded, page])

    useEffect(() => {
        const handleScroll = () => {
            if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
                setPage(prevPage => prevPage + 1)
            }
        }

        window.addEventListener('scroll', handleScroll)
        return () => window.removeEventListener('scroll', handleScroll)
    }, [])

    useEffect(() => {
        if (firstPageLoaded && page > 1) {
            setLoading(true)
            axios.get(`${URL}?page=${page}&api_key=${API_KEY}`)
                .then(response => {
                    setMovies(prevMovies => [...prevMovies, ...response.data.results])
                    setLoading(false)
                })
                .catch(error => {
                    console.log(error)
                    setLoading(false)
                    setErrorMessage(true)
                })
        }
    }, [firstPageLoaded, page])

    if (errorMessage) {
        return <div className='h-screen flex flex-col items-center justify-center space-y-3'>
                    <MdError className=' text-red-500 text-4xl'/>
                    <p className=' text-red-500 text-sm text-center md:text-base'>Hubo un problema en el servidor.<br />Por favor, inténtalo de nuevo más tarde.</p>
                </div>
    }

    return (
        <section className='px-4 text-white py-6 pb-20 lg:ml-48'>
            <h2 className=' text-xl font-bold pb-8'>Top Ranking</h2>
            <div className='flex justify-evenly flex-wrap gap-3 lg:gap-4 '>
                {loading && <span className="loading loading-spinner loading-lg text-white"></span>}
                {movies.map(movie => (
                    <Link to={`/Detalle/${movie.id}`} key={movie.id} className='relative mb-4'>
                        <div className=' absolute top-0 right-0 px-3 py-1 flex items-center bg-[#363434d8] text-white rounded-tr-2xl rounded-bl-3xl space-x-1'>
                            <FaStar className=' text-yellow-400' />
                            <p className=' text-sm font-medium'>{movie.vote_average.toFixed(1)}</p>
                        </div>
                        <img className='w-36 rounded-2xl lg:w-44' src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`} alt={movie.title} />
                    </Link>
                ))}
            </div>
        </section>
    )
}

export default TopRanking;
