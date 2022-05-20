package com.instagram.service.impl;

import com.instagram.controller.dto.CommentRequestDto;
import com.instagram.model.internal.CommentDto;
import com.instagram.repository.CommentRepository;
import com.instagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private static final String COMMENT_NOT_FOUND_MESSAGE = "Comment wasn't found by %s";

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void createComment(String username, UUID externalPostId, CommentRequestDto request) {
        commentRepository.save(request.createComment(username, externalPostId));
    }

    @Override
    public CommentDto getCommentByCommentId(UUID externalCommentId) {
        return commentRepository.findByCommentId(externalCommentId)
                .orElseThrow(() -> new IllegalArgumentException(String.format(COMMENT_NOT_FOUND_MESSAGE, "externalCommentId: " + externalCommentId)));
    }

    @Override
    public Page<CommentDto> getCommentsByPostId(UUID externalPostId, Pageable pageable) {
        return commentRepository.findByExternalPostId(externalPostId, pageable);
    }

    @Override
    public Page<CommentDto> getRecentCommentsByUsernameDesc(String username, Pageable pageable) {
        return commentRepository.findRecentCommentsByUsername(username, pageable);
    }

    @Override
    @Transactional
    public void updateCommentByCommentId(UUID externalCommentId, CommentRequestDto request) {
        commentRepository.updateByCommentId(externalCommentId, request.getContents());
    }

    @Override
    @Transactional
    public void deleteCommentByCommentId(UUID externalCommentId) {
        commentRepository.deleteByCommentId(externalCommentId);
    }
}