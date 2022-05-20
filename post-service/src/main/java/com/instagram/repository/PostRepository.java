package com.instagram.repository;

import com.instagram.model.entity.Post;
import com.instagram.model.projection.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT p FROM Post p WHERE p.isDeleted = false AND p.externalId = ?1")
    Optional<Post> findByExternalId(UUID externalPostId);

    @Query(value = "SELECT p.externalId AS externalId, p.username AS username, p.contentUrl AS contentUrl, p.caption AS caption, " +
            "p.location AS location, p.settings AS settings FROM Post p WHERE p.isDeleted = false AND p.externalId = ?1")
    Optional<PostDto> findPostDtoByExternalId(UUID externalPostId);

    @Query(value = "SELECT p.externalId AS externalId, p.username AS username, p.contentUrl AS contentUrl, p.caption AS caption, " +
            "p.location AS location, p.settings AS settings FROM Post p WHERE p.isDeleted = false AND p.username = ?1 ORDER BY p.createdAt DESC")
    Page<PostDto> findRecentPostsByUsername(String username, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Post p SET p.isDeleted = true WHERE p.externalId = ?1")
    void deleteByExternalId(UUID externalPostId);
}