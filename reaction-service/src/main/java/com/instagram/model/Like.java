package com.instagram.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_id_generator")
    @SequenceGenerator(name = "like_id_generator", allocationSize = 1)
    private Long id;

    private UUID externalId;

    private UUID externalPostId;

    private UUID externalCommentId;

    private String username;

    @CreationTimestamp
    private LocalDateTime createdAt;
}