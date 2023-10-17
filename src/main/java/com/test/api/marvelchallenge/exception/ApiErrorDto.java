package com.test.api.marvelchallenge.exception;

public record ApiErrorDto(
        String message,
        String backendMessage,
        String method,
        String url
) {
}
