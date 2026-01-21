package com.crinity.common.security;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        Integer statusCode,
        String message,
        String requestUri
) {
    public static ErrorResponse unauthorized(String requestUri) {
        return new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "인증이 만료되었습니다. 다시 로그인해주세요.",
                requestUri
        );
    }

    public static ErrorResponse forbidden(String requestUri) {
        return new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "접근 권한이 없습니다.",
                requestUri
        );
    }
}
