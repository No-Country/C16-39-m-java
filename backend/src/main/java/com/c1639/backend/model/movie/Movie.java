package com.c1639.backend.model.movie;



import com.c1639.backend.model.review.Review;
import com.c1639.backend.model.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name="Movie")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Movie {

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @ManyToMany(mappedBy = "favoriteMovies")
    private Set<User> users = new HashSet<>();

    @jakarta.persistence.Id
    @Id
    // Don't use @GeneratedValue here because the id is being set manually
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(length = 1000)
    private String overview;

    // Helper method
    public void addReview(Review review){
        this.reviews.add(review);
        review.setMovie(this);
    }

}
