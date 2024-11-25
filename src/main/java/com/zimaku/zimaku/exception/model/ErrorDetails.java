package com.zimaku.zimaku.exception.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorDetails(
        String title,
        int status,
        String detail,
        List<String> errors) {
}
