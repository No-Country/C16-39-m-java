package com.c1639.backend.mapper.movie;

import com.c1639.backend.dto.movie.MovieDTO;
import com.c1639.backend.model.movie.Movie;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    Movie toEntity(MovieDTO movieDTO);

    MovieDTO movieDTO(Movie movie);
}
