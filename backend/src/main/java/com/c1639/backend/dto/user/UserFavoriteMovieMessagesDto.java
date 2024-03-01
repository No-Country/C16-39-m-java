package com.c1639.backend.dto.user;

import java.io.Serializable;

/**
 * DTO for {@link com.c1639.backend.model.user.User}
 */
public record UserFavoriteMovieMessagesDto(
  boolean isError,
  String message

) implements Serializable { }