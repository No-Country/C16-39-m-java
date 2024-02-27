package com.c1639.backend.model.review;

import com.c1639.backend.model.comment.Comment;
import com.c1639.backend.model.movie.Movie;
import com.c1639.backend.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
@Entity
@Table (name = "review")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private Movie movie;
    private Integer rate;
    private Comment comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
