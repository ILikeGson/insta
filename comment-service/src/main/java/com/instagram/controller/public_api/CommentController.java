package com.instagram.controller.public_api;

import com.instagram.controller.dto.CommentRequestDto;
import com.instagram.model.internal.CommentDto;
import com.instagram.service.CommentService;
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
@RequestMapping(path = "/public/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/users/{username}/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@PathVariable("username") String username,
                              @PathVariable("postId") UUID externalPostId,
                              @RequestBody @Valid CommentRequestDto request) {
        log.info("Received a request for creating a comment: username: {}, postId: {}, request: {}", username, externalPostId, request);
        commentService.createComment(username, externalPostId, request);
    }

    @GetMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentByCommentId(@PathVariable("commentId") UUID externalCommentId) {
        log.info("Received a request for getting a comment by externalCommentId: {}", externalCommentId);
        return commentService.getCommentByCommentId(externalCommentId);
    }

    @GetMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentDto> getCommentsByPostId(@PathVariable("postId") UUID externalPostId,
                                                @PageableDefault(size = 20) Pageable pageable) {
        log.info("Received a request for getting comments by externalPostId: {}", externalPostId);
        return commentService.getCommentsByPostId(externalPostId, pageable);
    }

    @GetMapping("/users/{username}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentDto> getRecentCommentsByUsernameDesc(@PathVariable("username") String username,
                                                            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Received a request for getting comments by username: {}", username);
        return commentService.getRecentCommentsByUsernameDesc(username, pageable);
    }

    @PutMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCommentByCommentId(@PathVariable("commentId") UUID externalCommentId,
                                         @RequestBody @Valid CommentRequestDto request) {
        log.info("Received a request for updating a comment by externalCommentId: {}, {}", externalCommentId, request);
        commentService.updateCommentByCommentId(externalCommentId, request);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommentByCommentId(@PathVariable("commentId") UUID externalCommentId) {
        log.info("Received a request for deleting a comment by externalCommentId: {}", externalCommentId);
        commentService.deleteCommentByCommentId(externalCommentId);
    }
}