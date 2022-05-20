package com.instagram.model.projection;

import java.util.UUID;

public interface PostDto {
    UUID getExternalId();
    String getUsername();
    String getContentUrl();
    String getCaption();
    LocationDto getLocation();
    PostSettingsDto getSettings();

    interface LocationDto {
        String getCountry();
        String getCity();
        String getStreet();
        String getHouse();
    }

    interface PostSettingsDto {
        boolean getHideLikesAndViews();
        boolean getTurnOffComments();
    }
}