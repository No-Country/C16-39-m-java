package com.c1639.backend.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.c1639.backend.model.user.User}
 */
public record UserToLoginDto(

  @NotBlank(message = "El email del usuario es requerido")
  String email,
  @NotBlank(message = "El password del usuario es requerido")
    String password

) implements Serializable {
}