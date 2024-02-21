package com.c1639.backend.model.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@AllArgsConstructor
@Builder

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;


    public Comment(String content) {
        this.content = content;
    }

    public Comment() {
        this.id = id;
    }
}
