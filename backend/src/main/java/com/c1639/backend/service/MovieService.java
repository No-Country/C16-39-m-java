package com.c1639.backend.service;

import com.c1639.backend.model.Movie;
import com.c1639.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie); // Saves the movie to the database
    }

    public Movie updateMovie(Integer id, Movie updatedMovie) {
        Movie movie = movieRepository.findById(id).orElseThrow(); // find the existing movie or throw an exception
        movie.setTitle(updatedMovie.getTitle());
        movie.setReleaseYear(updatedMovie.getReleaseYear());
        movie.setOverview(updatedMovie.getOverview());
        movie.setPosterPath(updatedMovie.getPosterPath());
        movie.setBackdropPath(updatedMovie.getBackdropPath());
        movie.setActive(updatedMovie.isActive()); // no me deja usar el get, raro
        return movieRepository.save(movie); // save the updated movie
    }

    // Retrieve all movies from the database
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Find the movie by ID or throw an exception
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow();
    }

    // future methods, maybe not

}

