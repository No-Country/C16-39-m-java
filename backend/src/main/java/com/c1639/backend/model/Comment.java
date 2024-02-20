package com.c1639.backend.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor

public class Comment {

    private Long id;
    private String content;





    public Comment(Long id) {
        this.id = id;
    }
}
