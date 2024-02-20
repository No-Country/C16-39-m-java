package com.c1639.backend.controller;

import com.c1639.backend.dto.user.UserSignedUpDto;
import com.c1639.backend.dto.user.UserToSignUpDto;
import com.c1639.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
