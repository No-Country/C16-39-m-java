package com.c1639.backend.repository;

import com.c1639.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmailAndActiveTrue(String email);

    boolean existsByEmailAndActiveTrue(String email);
}
