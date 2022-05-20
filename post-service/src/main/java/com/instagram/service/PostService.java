package com.instagram.service;

import com.instagram.controller.dto.PostRequestDto;
import com.instagram.model.projection.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PostService {
    PostDto getPostByPostId(UUID externalPostId);
    Page<PostDto> getRecentPageablePostsByUsername(String username, Pageable pageable);
    void createPost(String username, PostRequestDto request);
    void updatePostByPostId(UUID externalPostId, PostRequestDto request);
    void deletePostByPostId(UUID externalPostId);
}
