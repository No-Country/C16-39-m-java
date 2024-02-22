package com.c1639.backend.model.movie;



import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.sql.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="Movie")

public class Movie {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;
    @Column
    private Date releaseYear;
    @Column
    private String posterPath;
    @Column
    private String backdropPath;
    @Column
    private boolean active;
    @Column
    private String overview;

}
