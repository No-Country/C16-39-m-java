package com.c1639.backend.model.user;

import com.c1639.backend.model.movie.Movie;
import com.c1639.backend.model.review.Review;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private Role role;

    private boolean active;

    /*One User has many Reviews (A user row can be referenced by multiple review rows)
    * The "user_id" column in the "review" table column maps this relationship via a foreign key that references the primary key of the "users" table.
    * A Review cannot exist without a User, so the CascadeType.ALL and orphanRemoval = true options are set to ensure
    * that the Review entity is deleted when the User entity is deleted.
    * The `User` entity is the parent side of the relationship, so it is the owning side of the relationship.
    * While the `Review` entity is the child side of the relationship, so it is the inverse side of the relationship.
    * */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    //****** Helper Methods for Reviews: Keep Both Sides of the Association in SYNC.********/

    /** Add a review to the list of reviews
     * @param review the review to add */
    public void addReview(Review review) {
        this.reviews.add(review);
        review.setUser(this);
    }

    /** Remove a review from the list of reviews
     * @param review the review to remove */
    public void removeReview(Review review) {
        review.setUser(null);
        this.reviews.remove(review);
    }
    //********************End Helper Methods********************************************/

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "favorites", // name of the join table
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> favoriteMovies = new HashSet<>();

    public void addFavoriteMovie(Movie movie) {
        this.favoriteMovies.add(movie);
        movie.getUsers().add(this);
    }
    public void removeFavoriteMovie(Movie movie) {
        this.favoriteMovies.remove(movie);
        movie.getUsers().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return active == user.active && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, role, active);
    }

    @Override
    public String toString() {
        return "User{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", email='" + email + '\'' +
          ", password='" + password + '\'' +
          ", role=" + role +
          ", isActive=" + active +
          '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
