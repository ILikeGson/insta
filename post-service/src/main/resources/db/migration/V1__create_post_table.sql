CREATE SEQUENCE post_id_generator START 1 INCREMENT 1;

CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    external_id UUID NOT NULL UNIQUE,
    username VARCHAR(64) NOT NULL,
    content_url VARCHAR(256) NOT NULL UNIQUE,
    caption VARCHAR(200),
    created_at TIMESTAMP NOT NULL,
    modified_AT TIMESTAMP NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT false,
    country VARCHAR(64),
    city VARCHAR(64),
    street VARCHAR(64),
    house VARCHAR(64),
    hide_likes_and_views BOOLEAN NOT NULL DEFAULT false,
    turn_off_comments BOOLEAN NOT NULL DEFAULT false
);