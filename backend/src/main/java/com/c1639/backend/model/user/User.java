package com.c1639.backend.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private Role role;

    private boolean active;

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
}
