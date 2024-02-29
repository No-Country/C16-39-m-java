package com.c1639.backend.repository;

import com.c1639.backend.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

    boolean existsByIdAndActiveTrue(Long id);

    Movie findByIdAndActiveTrue(Long id);

}
