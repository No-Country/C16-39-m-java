package com.c1639.backend.controller;

import com.c1639.backend.dto.comment.CommentDto;
import com.c1639.backend.dto.movie.ListMoviesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.c1639.backend.model.comment.Comment;

@Tag(name = "Comment", description = "Manage all endpoints about Comments")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor


public class CommentController {


    @Operation(
            summary = "Create a Comment.",
            description = "Allows the user to create a comment as long as they have a review."
    )

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User created successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ListMoviesDTO.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Comment Already Exists", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content})
    })
    @PostMapping
    public Comment createComment(@RequestBody CommentDto comment) {
        return new Comment("");
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a comment by ID",
            description = "Allows user to update a comment by ID"
    )
    public Comment updateComment(@PathVariable int id, @RequestBody CommentDto updatedComment) {

        return new Comment("");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a comment by ID",
            description = "Allows the user to search by ID"
    )

    public Comment getCommentById(@PathVariable int id) {
        Comment comment = new Comment();
        comment.setContent("Contenido del comentario con ID " + id);
        return comment;
    }
}
