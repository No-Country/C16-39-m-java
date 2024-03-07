package com.c1639.backend.dto.review;

import com.c1639.backend.model.comment.Comment;
import com.c1639.backend.model.movie.Movie;
import com.c1639.backend.model.user.User;

public class ReviewDTO {
    User user;
    Movie movie;
    Integer rate;
    Comment comment;
}
