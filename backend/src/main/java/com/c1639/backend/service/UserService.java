package com.c1639.backend.service;

import com.c1639.backend.dto.movie.MovieDTO;
import com.c1639.backend.dto.user.*;
import com.c1639.backend.exception.user.UserAlreadyExistsException;
import com.c1639.backend.exception.user.UserDataLoginException;
import com.c1639.backend.exception.user.UserNotFoundException;
import com.c1639.backend.mapper.movie.MovieMapper;
import com.c1639.backend.mapper.user.UserMapper;
import com.c1639.backend.model.movie.Movie;
import com.c1639.backend.model.user.Role;
import com.c1639.backend.model.user.User;
import com.c1639.backend.repository.MovieRepository;
import com.c1639.backend.repository.UserRepository;
import com.c1639.backend.security.PasswordEncoder;
import com.c1639.backend.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public String welcome() {
        return "Welcome to the User Service";
    }

    public UserSignedUpDto signUp(UserToSignUpDto userToSignUpDto) {

        //Check if the user already exists
        if (userRepository.existsByEmailAndActiveTrue(userToSignUpDto.email()))
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
        if (!userRepository.existsByEmailAndActiveTrue(userEmail))
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
          false,
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

    public UserFavoriteMovieMessagesDto createFavorite(MovieDTO movieDto, HttpServletRequest request) {

        User user = getUserFromDatabase(request);
        boolean existsMovie = movieRepository.existsByIdAndActiveTrue(movieDto.id());

        Movie movie = movieRepository.findByIdAndActiveTrue(movieDto.id());

        if (!existsMovie)
            movie = movieRepository.save(movieMapper.toEntity(movieDto));

        user.addFavoriteMovie(movie);

        return new UserFavoriteMovieMessagesDto(
          false,
          "Pelicula favorita guardada exitosamente"
        );

    }

    public Page<MovieDTO> getFavorites(Pageable pageable, HttpServletRequest request) {

        User user = getUserFromDatabase(request);

        List<MovieDTO> favoriteMoviesList = user
          .getFavoriteMovies()
          .stream()
          .map(movieMapper::movieDTO).distinct().collect(Collectors.toList());

        if (favoriteMoviesList.isEmpty()) {
            throw new UserNotFoundException("No se encontraron películas favoritas");
        }

        // Calculates paging indexes
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<MovieDTO> pageList;

        if (favoriteMoviesList.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, favoriteMoviesList.size());
            pageList = favoriteMoviesList.subList(startItem, toIndex);
        }

        // Create an object Page from the paged list and the provided pageable
        return new PageImpl<>(pageList, pageable, favoriteMoviesList.size());

    }

    public UserFavoriteMovieMessagesDto deleteFavorite(Long idMovie, HttpServletRequest request) {

        User user = getUserFromDatabase(request);
        Movie movie = movieRepository.findByIdAndActiveTrue(idMovie);

        if (movie == null) {
            return new UserFavoriteMovieMessagesDto(
              true,
              "La película no existe en la base de datos"
            );
        }

        boolean isEmptyMovie = user.getFavoriteMovies().stream().filter(m -> m.getId().equals(idMovie)).findFirst().isEmpty();

        if (isEmptyMovie) {
            return new UserFavoriteMovieMessagesDto(
              true,
              "La película no existe en la lista de favoritos"
            );
        }

        user.removeFavoriteMovie(movie);

        return new UserFavoriteMovieMessagesDto(
          false,
          "Pelicula favorita eliminada exitosamente"
        );
    }

    private User getUserFromDatabase(HttpServletRequest request) {
        String token = tokenService.getTokenFromHeader(request); //Get token from the header
        String userEmail = tokenService.getVerifier(token).getSubject(); //Get the user email from the token

        boolean existsUser = userRepository.existsByEmailAndActiveTrue(userEmail);

        if (!existsUser) {
            throw new UserNotFoundException("User not found in the database");
        }

        return (User) userRepository.findByEmailAndActiveTrue(userEmail);
    }
}
