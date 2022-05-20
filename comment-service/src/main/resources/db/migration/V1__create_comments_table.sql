CREATE SEQUENCE comment_id_generator START 1 INCREMENT 1;

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    external_id UUID NOT NULL UNIQUE,
    external_post_id UUID NOT NULL,
    username VARCHAR(64) NOT NULL,
    contents VARCHAR(256) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT false,
    is_modified BOOLEAN NOT NULL DEFAULT false
);