package com.c1639.backend.service;

import com.c1639.backend.dto.user.LoggedUserDto;
import com.c1639.backend.dto.user.UserSignedUpDto;
import com.c1639.backend.dto.user.UserToLoginDto;
import com.c1639.backend.dto.user.UserToSignUpDto;
import com.c1639.backend.exception.user.UserAlreadyExistsException;
import com.c1639.backend.exception.user.UserDataLoginException;
import com.c1639.backend.exception.user.UserNotFoundException;
import com.c1639.backend.mapper.user.UserMapper;
import com.c1639.backend.model.user.Role;
import com.c1639.backend.model.user.User;
import com.c1639.backend.repository.UserRepository;
import com.c1639.backend.security.PasswordEncoder;
import com.c1639.backend.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public String welcome() {
        return "Welcome to the User Service";
    }

    public UserSignedUpDto signUp(UserToSignUpDto userToSignUpDto) {

        //Check if the user already exists
        if (userRepository.existsByEmailAndActiveIsTrue(userToSignUpDto.email()))
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

    public LoggedUserDto login(UserToLoginDto userToLoginDto) {

        // Get the user email
        String userEmail = userToLoginDto.email();

        // Check if the user exists
        if (!userRepository.existsByEmailAndActiveIsTrue(userEmail))
            throw new UserNotFoundException("El usuario no existe en la base de datos");

        // Get hashed password from the database
        String hashedPassword = userRepository.findByEmailAndActiveTrue(userEmail).getPassword();

        // Check if the password is correct
        boolean passwordMatches = PasswordEncoder.verifyPassword(userToLoginDto.password(), hashedPassword);

        if (!passwordMatches)
            throw new UserDataLoginException("El email o la contraseña es incorrecta.");

        // Auth Credentials
        Authentication auth = new UsernamePasswordAuthenticationToken(
            userToLoginDto.email(),
            userToLoginDto.password()
        );

        // Generate the token
        User authUser = (User) authenticationManager.authenticate(auth).getPrincipal();
        String token = tokenService.generateToken(authUser);

        return new LoggedUserDto(
          authUser.getId(),
          authUser.getName(),
          authUser.getEmail(),
          token
        );
    }

    //Get user by token
    public UserSignedUpDto getUser(HttpServletRequest request) {

        User user = getUserFromDatabase(request);

        return userMapper.userToUserSignedUpDto(user);
    }

    private User getUserFromDatabase(HttpServletRequest request) {
        String token = tokenService.getTokenFromHeader(request); //Get token from the header
        String userEmail = tokenService.getVerifier(token).getSubject(); //Get the user email from the token

        if (!userRepository.existsByEmailAndActiveIsTrue(userEmail))
            throw new UserNotFoundException("User not found in the database");

        return (User) userRepository.findByEmailAndActiveTrue(userEmail);
    }
}
