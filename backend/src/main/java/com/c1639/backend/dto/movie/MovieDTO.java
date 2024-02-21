package com.c1639.backend.dto.movie;

public record MovieDTO(Integer id,
                       String releaseYear,
                       String posterPath,
                       String backdropPath,
                       String title,
                       boolean active,
                       String overview) {


}
