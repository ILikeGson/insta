package com.instagram.model.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String country;
    private String city;
    private String street;
    private String house;
}