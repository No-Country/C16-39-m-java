package com.c1639.backend.service;

import com.c1639.backend.model.comment.Comment;
import com.c1639.backend.dto.comment.CommentDto;
import com.c1639.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(CommentDto commentDto) {
        Comment comment = new Comment(commentDto.getContent());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, CommentDto updatedCommentDto) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment with id " + id + " not found"));
        existingComment.setContent(updatedCommentDto.getContent());
        return commentRepository.save(existingComment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment with id " + id + " not found"));
    }
}
