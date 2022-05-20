package com.instagram.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instagram.model.entity.Post;
import com.instagram.model.internal.Location;
import com.instagram.model.internal.PostSettings;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostRequestDto {

    @NotBlank(message = "Content must be specified")
    @Size(min = 10, max = 255)
    String contentUrl;

    @Size(max = 200, message = "Size must be less than or equal to 200")
    String caption;

    String country;
    String city;
    String street;
    String house;
    boolean hideLikesAndViews;
    boolean turnOffComments;

    public Post createPost(String username) {
        return Post.builder()
                .externalId(UUID.randomUUID())
                .username(username)
                .contentUrl(contentUrl)
                .caption(caption)
                .location(Location.builder()
                            .city(city)
                            .country(country)
                            .street(street)
                            .house(house)
                            .build())
                .settings(PostSettings.builder()
                            .hideLikesAndViews(hideLikesAndViews)
                            .turnOffComments(turnOffComments)
                            .build())
                .build();
    }
}