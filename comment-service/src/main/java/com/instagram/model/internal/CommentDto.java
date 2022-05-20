package com.instagram.model.internal;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CommentDto {
    UUID getExternalId();
    UUID getExternalPostId();
    String getContents();
    String getUsername();
    LocalDateTime getCreatedAt();
    Boolean getIsModified();
}