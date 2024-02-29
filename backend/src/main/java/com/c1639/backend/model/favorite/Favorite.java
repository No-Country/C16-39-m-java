package com.c1639.backend.model.favorite;

import com.c1639.backend.model.movie.Movie;
import com.c1639.backend.model.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    // OneToMany relationship needs to be added to the User model
    @ManyToOne
    private Movie movie;

    @ManyToOne User user;


}
