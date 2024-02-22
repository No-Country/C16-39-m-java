package com.c1639.backend.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.sql.Date;

@Entity
@Data
@RequiredArgsConstructor


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
