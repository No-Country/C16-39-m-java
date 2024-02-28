import React, { useEffect } from 'react';
import axios from 'axios'
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { FaStar } from "react-icons/fa";

const TopRanking = () => {

    const URL = 'https://api.themoviedb.org/3/movie/top_rated'
    const API_KEY = import.meta.env.VITE_API_KEY

    const [movies, setMovies] = useState([])

    useEffect(() => {
        axios.get(`${URL}?page=1&api_key=${API_KEY}`)
            .then(response => {
                setMovies([...response.data.results])
                
            })
            .catch(error => {
                console.log(error)
            })
    }, [])

    return (
        <section className='px-4 text-white py-6 pb-20 lg:ml-48'>
            <h2 className=' text-xl font-bold pb-8'>Top Ranking</h2>
            <div className='flex justify-evenly flex-wrap gap-3 lg:gap-4 '>
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
