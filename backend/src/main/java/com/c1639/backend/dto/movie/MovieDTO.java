package com.c1639.backend.dto.movie;

import java.util.Date;

public record MovieDTO(
  Long id,
  String title,
  Date releaseYear,
  String posterPath,
  String backdropPath,
  boolean active,
  String overview) {

    public MovieDTO {
        active = true;
    }
}
