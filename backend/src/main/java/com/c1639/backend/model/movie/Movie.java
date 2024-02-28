package com.c1639.backend.model.movie;



import com.c1639.backend.model.review.Review;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="Movie")

public class Movie {

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

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
