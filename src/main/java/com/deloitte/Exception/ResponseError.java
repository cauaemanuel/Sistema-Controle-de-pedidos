package com.deloitte.Exception;

import org.springframework.http.HttpStatus;

public record ResponseError(HttpStatus httpStatus, String message) {
}