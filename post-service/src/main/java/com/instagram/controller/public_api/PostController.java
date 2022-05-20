package com.instagram.controller.public_api;

import com.instagram.controller.dto.PostRequestDto;
import com.instagram.model.entity.Post;
import com.instagram.model.projection.PostDto;
import com.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/public/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @GetMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostByPostId(@PathVariable("postId") UUID externalPostId) {
        log.info("Received a request for getting a post by externalPostId: {}", externalPostId);
        return postService.getPostByPostId(externalPostId);
    }

    @GetMapping("/users/{username}/posts")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDto> getRecentPageablePostsByUsername(@PathVariable("username") String username,
                                                          @PageableDefault(size = 20) Pageable pageable) {
        log.info("Received a request for getting a recent pageable post by username and pageable: {}, {}", username, pageable);
        return postService.getRecentPageablePostsByUsername(username, pageable);
    }

    @PostMapping("/users/{username}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@PathVariable("username") String username, @RequestBody @Valid PostRequestDto request) {
        log.info("Received a request for creating a post by username: {}, {}", username, request);
        postService.createPost(username, request);
    }

    @PutMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePostByPostId(@PathVariable("postId") UUID externalPostId, @RequestBody @Valid PostRequestDto request) {
        log.info("Received a request for updating a post by postId: {}, {}", externalPostId, request);
        postService.updatePostByPostId(externalPostId, request);
    }

    @DeleteMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePostByPostId(@PathVariable("postId") UUID externalPostId) {
        log.info("Received a request for deleting a post by externalPostId");
        postService.deletePostByPostId(externalPostId);
    }
}
