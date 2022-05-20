package com.instagram.model.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PostSettings {
    boolean hideLikesAndViews;
    boolean turnOffComments;
}
