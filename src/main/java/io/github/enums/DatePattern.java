package io.github.enums;

import lombok.Getter;

@Getter
public enum DatePattern {
    UTC("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"),
    DATE_TIME("MMM d, yyyy-h:mm a"),
    AM_PM("M/dd/yyyy h:mm a"),
    UTC_TIME("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    DATE("yyyy-MM-dd");

    private final String pattern;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }
}
