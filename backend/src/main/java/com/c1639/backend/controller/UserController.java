package com.c1639.backend.controller;

import com.c1639.backend.dto.movie.MovieDTO;
import com.c1639.backend.dto.user.*;
import com.c1639.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Users", description = "Manage all endpoints about Users")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
      summary = "Get a welcome message.",
      description = "Let a user get a welcome message."
    )
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Welcome was successfully.", content = {@Content}),
      @ApiResponse(responseCode = "403", description = "Forbidden access to this resource", content = {@Content}),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content})
    })
    @SecurityRequirements()
    @GetMapping("/welcome")
    public ResponseEntity<String> getUser() {
        return ResponseEntity
          .status(HttpStatus.OK)
          .body(userService.welcome());
    }

    @Operation(
      summary = "Sign up a new User.",
      description = "Let a user sign up with an email account."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "User created successfully",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserSignedUpDto.class))
        }),
      @ApiResponse(responseCode = "400", description = "User Already Exists", content = {@Content}),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @SecurityRequirements()
    @PostMapping()
    @Transactional
    public ResponseEntity<UserSignedUpDto> signUp(
      @RequestBody @Valid UserToSignUpDto userToSignUpDto,
      UriComponentsBuilder uriComponentsBuilder
    ) {
        UserSignedUpDto userSignedUpDto = userService.signUp(userToSignUpDto);

        URI location = uriComponentsBuilder
        .path("/users/{id}")
        .buildAndExpand(userSignedUpDto.id())
        .toUri();

        return ResponseEntity
        .created(location)
        .body(userSignedUpDto);
    }

    @Operation(
      summary = "User Login section.",
      description = "Let a user login with the email account. Return a token"
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "User logged successfully",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = LoggedUserDto.class))
        }),
      @ApiResponse(responseCode = "400", description = "User data login incorrect", content = {@Content}),
      @ApiResponse(responseCode = "404", description = "User Not Found", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @SecurityRequirements()
    @PostMapping("/auth")
    @Transactional
    public ResponseEntity<LoggedUserDto> login(@RequestBody @Valid UserToLoginDto userToLoginDto) {

        return ResponseEntity
          .status(HttpStatus.OK)
          .body(userService.login(userToLoginDto));
    }

    @Operation(
      summary = "User gets its data using auth token.",
      description = "Let a logged user get its data using the authorization token."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "User found successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserSignedUpDto.class))
        }),
      @ApiResponse(responseCode = "403", description = "Forbidden access to this resource", content = {@Content}),
      @ApiResponse(responseCode = "404", description = "User Not Found", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @GetMapping("/me")
    public ResponseEntity<UserSignedUpDto> getUser(HttpServletRequest request) {
        return ResponseEntity
          .status(HttpStatus.OK)
          .body(userService.getUser(request));
    }

    /* Endpoints for: /users/favorites */

    @Operation(
      summary = "Save a movie like favorite.",
      description = "Let a user save a movie in favorite list."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "201", description = "Favorite movie saved successfully",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserFavoriteMovieMessagesDto.class))
        }),
      @ApiResponse(responseCode = "403", description = "Forbidden access to this resource", content = {@Content}),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @PostMapping("/favorites")
    @Transactional
    public ResponseEntity<UserFavoriteMovieMessagesDto> createFavorite(
        @RequestBody @Valid MovieDTO movieDto,
        UriComponentsBuilder uriComponentsBuilder,
        HttpServletRequest request
    ) {

        UserFavoriteMovieMessagesDto userFavoriteCreatedMovieDto = userService.createFavorite(movieDto, request);

        URI location = uriComponentsBuilder
          .path("/users/favorites/movie/{id}")
          .buildAndExpand(movieDto.id())
          .toUri();

        return ResponseEntity
          .created(location)
          .body(userFavoriteCreatedMovieDto);
    }

    @Operation(
      summary = "Get the favorite movies list.",
      description = "Let a user get the favorite movie list. Token is required. "
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "201", description = "Favorite movie list successfully generated",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = MovieDTO.class))
        }),
      @ApiResponse(responseCode = "403", description = "Forbidden access to this resource", content = {@Content}),
      @ApiResponse(responseCode = "404", description = "Favorite movie list Not Found", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @Parameters({
      @Parameter(name = "page", description = "Page number", required = false, example = "0"),
      @Parameter(name = "size", description = "Size of the page", required = false, example = "10"),
      @Parameter(name = "sort", description = "Sort the page", required = false, example = "id,desc")
    })
    @GetMapping("/favorites")
    public ResponseEntity<Page<MovieDTO>> getFavorites(Pageable pageable, HttpServletRequest request) {

        return ResponseEntity
          .status(HttpStatus.OK)
          .body(userService.getFavorites(pageable, request));
    }

    @Operation(
      summary = "Remove a movie from the favorite movies list.",
      description = "Let a user remove a movie from the favorite movies list. Token is required. "
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Movie successfully removed from favorite list",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserFavoriteMovieMessagesDto.class))
        }),
      @ApiResponse(responseCode = "403", description = "Forbidden access to this resource", content = {@Content}),
      @ApiResponse(responseCode = "404", description = "Movie not found in favorite movie list", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @Parameters({
        @Parameter(name = "idMovie", description = "Movie id", required = true, example = "745891")
    })
    @DeleteMapping("/favorites/{idMovie}")
    @Transactional
    public ResponseEntity<UserFavoriteMovieMessagesDto> deleteFavorite(@PathVariable Long idMovie, HttpServletRequest request) {
        return ResponseEntity.status(200).body(userService.deleteFavorite(idMovie, request));
    }
}
