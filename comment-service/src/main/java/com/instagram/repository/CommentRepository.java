package com.instagram.repository;

import com.instagram.model.entity.Comment;
import com.instagram.model.internal.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c.externalId AS externalId, c.username AS username, c.contents AS contents, c.externalPostId AS externalPostId, " +
            "c.createdAt AS createdAt, c.isModified AS isModified FROM Comment c WHERE c.isDeleted = false AND c.externalId = ?1")
    Optional<CommentDto> findByCommentId(UUID externalCommentId);

    @Query(value = "SELECT c.externalId AS externalId, c.username AS username, c.contents AS contents, c.externalPostId AS externalPostId, " +
            "c.createdAt AS createdAt, c.isModified AS isModified FROM Comment c WHERE c.isDeleted = false AND c.externalPostId = ?1 ORDER BY c.createdAt ASC")
    Page<CommentDto> findByExternalPostId(UUID externalPostId, Pageable pageable);

    @Query(value = "SELECT c.externalId AS externalId, c.username AS username, c.contents AS contents, c.externalPostId AS externalPostId, " +
            "c.createdAt AS createdAt, c.isModified AS isModified FROM Comment c WHERE c.isDeleted = false AND c.username = ?1 ORDER BY c.createdAt DESC")
    Page<CommentDto> findRecentCommentsByUsername(String username, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Comment c SET c.isModified = true, c.contents = ?2 WHERE c.externalId = ?1")
    void updateByCommentId(UUID externalCommentId, String contents);

    @Modifying
    @Query(value = "UPDATE Comment c SET c.isDeleted = true WHERE c.externalId = ?1")
    void deleteByCommentId(UUID externalCommentId);
}