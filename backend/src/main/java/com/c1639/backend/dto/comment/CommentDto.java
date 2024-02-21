package com.c1639.backend.dto.comment;

public record CommentDto() {

    private static String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
