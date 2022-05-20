package com.instagram.model.projection;

public interface UserProjection {
    String getName();
    String getUsername();
    String getPhotoUrl();
    String getBio();
    String getWebsite();
    String getEmail();
    String getPhoneNumber();
    String getGender();
    Integer getFollowersNumber();
    Integer getFollowingsNumber();
}