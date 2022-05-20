package com.instagram.service;

import com.instagram.controller.dto.CommentRequestDto;
import com.instagram.model.internal.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentService {
    void createComment(String username, UUID externalPostId, CommentRequestDto request);
    CommentDto getCommentByCommentId(UUID externalCommentId);
    Page<CommentDto> getCommentsByPostId(UUID externalPostId, Pageable pageable);
    Page<CommentDto> getRecentCommentsByUsernameDesc(String username, Pageable pageable);
    void updateCommentByCommentId(UUID externalCommentId, CommentRequestDto request);
    void deleteCommentByCommentId(UUID externalCommentId);
}