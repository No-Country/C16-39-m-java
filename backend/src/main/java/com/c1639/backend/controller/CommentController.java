package com.c1639.backend.controller;
import com.c1639.backend.dto.comment.CommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.c1639.backend.model.comment.Comment;

@Tag(name = "Comment", description = "Manage all endpoints about Comments")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    @PostMapping
    @Operation(
            summary = "Create a Comment.",
            description = "Allows the user to create a comment as long as they have a review."
    )
    public Comment createComment(@RequestBody CommentDto comment) {
        return comment;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a comment by ID")
    public Comment updateComment(@PathVariable int id, @RequestBody CommentDto updatedComment) {

        return updatedComment;
    }

    @GetMapping("/{id}")
    @ApiDescripcio(value = "Get a comment by ID")
    public Comment getCommentById(@PathVariable int id) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent("Contenido del comentario con ID " + id);
        return comment;
    }
}
