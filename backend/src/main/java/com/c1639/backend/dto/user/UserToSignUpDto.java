package com.c1639.backend.dto.user;

import com.c1639.backend.model.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.c1639.backend.model.user.User}
 */
public record UserToSignUpDto(

  @NotBlank(message = "El nombre del usuario no puede venir vacío")
  String name,
  @NotBlank(message = "El correo del usuario no puede venir vacío")
  @Email(message = "El correo debe ser un correo válido")
  String email,
  @NotBlank(message = "La contraseña del usuario no puede venir vacía")
  @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
  String password,
  boolean active,
  Role role

) implements Serializable {

  public UserToSignUpDto {

    active = true;

    if (role == null) {
      role = Role.USER;
    }

  }
}