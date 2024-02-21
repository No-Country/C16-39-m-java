package com.c1639.backend.dto.user;

import com.c1639.backend.model.user.Role;

import java.io.Serializable;

/**
 * DTO for {@link com.c1639.backend.model.user.User}
 */
public record LoggedUserDto(
  boolean isError,
  Long id,
  String name,
  String email,
  String token

) implements Serializable {
    public LoggedUserDto {
        isError = false;
    }
}