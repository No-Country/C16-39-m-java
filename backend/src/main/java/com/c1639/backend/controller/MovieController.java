package com.c1639.backend.controller;


import com.c1639.backend.dto.movie.ListMoviesDTO;
import com.c1639.backend.model.Movie;
import com.c1639.backend.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name="Movie", description = "Manage all movie endpoints")
@RequestMapping("/movie")
@RestController

public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(
            summary = "Sign up a new User.",
            description = "Let a user sign up with an email account."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User created successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ListMoviesDTO.class))
                    }),
            @ApiResponse(responseCode = "400", description = "User Already Exists", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/new")
    public Movie createMovie(@RequestBody @Valid Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody @Valid Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        return ResponseEntity.ok(updatedMovie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById (@PathVariable Integer id){
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

}
