package com.c1639.backend.repository;

import com.c1639.backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
