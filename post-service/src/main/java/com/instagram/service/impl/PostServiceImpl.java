package com.instagram.service.impl;

import com.instagram.controller.dto.PostRequestDto;
import com.instagram.model.entity.Post;
import com.instagram.model.internal.Location;
import com.instagram.model.internal.PostSettings;
import com.instagram.model.projection.PostDto;
import com.instagram.repository.PostRepository;
import com.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final String POST_NOT_FOUND_MESSAGE = "Post has not been found by externalId: %s";

    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public PostDto getPostByPostId(UUID externalPostId) {
        return postRepository.findPostDtoByExternalId(externalPostId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, externalPostId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostDto> getRecentPageablePostsByUsername(String username, Pageable pageable) {
        return postRepository.findRecentPostsByUsername(username, pageable);
    }

    @Override
    @Transactional
    public void createPost(String username, PostRequestDto request) {
        postRepository.save(request.createPost(username));
    }

    @Override
    @Transactional
    public void updatePostByPostId(UUID externalPostId, PostRequestDto request) {
        Post post = postRepository.findByExternalId(externalPostId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, externalPostId)));
        post.setCaption(request.getCaption());
        post.setLocation(Location.builder()
                .country(request.getCountry())
                .city(request.getCity())
                .street(request.getStreet())
                .house(request.getHouse())
                .build());
        post.setSettings(PostSettings.builder()
                .turnOffComments(request.isTurnOffComments())
                .hideLikesAndViews(request.isHideLikesAndViews())
                .build());
    }

    @Override
    @Transactional
    public void deletePostByPostId(UUID externalPostId) {
        postRepository.deleteByExternalId(externalPostId);
    }
}
