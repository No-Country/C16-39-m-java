package com.c1639.backend.controller;

import com.c1639.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
