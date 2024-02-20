package com.c1639.backend.service;

import com.c1639.backend.dto.user.UserSignedUpDto;
import com.c1639.backend.dto.user.UserToSignUpDto;
import com.c1639.backend.exception.user.UserAlreadyExistsException;
import com.c1639.backend.mapper.user.UserMapper;
import com.c1639.backend.model.user.Role;
import com.c1639.backend.model.user.User;
import com.c1639.backend.repository.UserRepository;
import com.c1639.backend.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public String welcome() {
        return "Welcome to the User Service";
    }

    public UserSignedUpDto signUp(UserToSignUpDto userToSignUpDto) {

        //Check if the user already exists
        if (userRepository.existsByEmail(userToSignUpDto.email()))
            throw new UserAlreadyExistsException("El usuario ya existe en la base de datos");

        // Get the plain password
        String plainPassword = userToSignUpDto.password();

        // Generate the password hash
        String hashedPassword = PasswordEncoder.generatePasswordHash(plainPassword);

        // Map the DTO data to the entity
        User user = userMapper.toEntity(userToSignUpDto);

        // Assign the password hash to the entity
        user.setPassword(hashedPassword);

        // Set the user role
        if (userToSignUpDto.role().name().equals(Role.ADMIN.name())) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        // Save the user in the database
        User userDB = userRepository.save(user);

        // Map the entity data to a DTO and return it
        return userMapper.userToUserSignedUpDto(userDB);

    }
}
