package com.instagram.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.instagram.model.entity.Comment;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentRequestDto {
    @JsonProperty("contents")
    String contents;

    @JsonCreator
    public CommentRequestDto(@NotBlank(message = "Content can not be blank")
                             @Size(min = 1, max = 255, message = "Content's length should be greater than 1 and less than 256")
                                     String contents) {
        this.contents = contents;
    }

    public Comment createComment(String username, UUID externalPostId) {
        return Comment.builder()
                .externalId(UUID.randomUUID())
                .externalPostId(externalPostId)
                .username(username)
                .contents(contents)
                .build();
    }
}