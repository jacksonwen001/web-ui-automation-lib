package io.github.enums;

import lombok.Getter;

@Getter
public enum DatePattern {
    /**
     * 常见的 UTC 时间 格式
     */
    UTC("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"),
    DATE_TIME("MMM d, yyyy-h:mm a"),
    AM_PM("M/d/yyyy h:mm a"),
    UTC_TIME("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    DATE("yyyy-MM-dd");

    private final String pattern;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }
}
