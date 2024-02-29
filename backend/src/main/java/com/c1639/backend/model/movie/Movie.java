package com.c1639.backend.model.movie;



import com.c1639.backend.model.review.Review;
import com.c1639.backend.model.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
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

    public void addReview(Review review){
        this.reviews.add(review);
        review.setMovie(this);
    }

}
