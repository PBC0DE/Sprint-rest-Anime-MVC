package com.example.sprintrestanimemvc.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public String handle(HttpClientErrorException e) {
        log.warn(e.getStatusCode().value() + ": message = {}", e.getMessage());
        return "error/" + e.getStatusCode().value();
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public String handle(HttpServerErrorException e) {
        log.error(e.getStatusCode().value() + ": message = {}", e.getMessage());
        return "error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handle(AccessDeniedException e) {
        log.warn("Access denied: message = {}", e.getMessage());
        return "error/403";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        log.error("Unexpected error occurred: message = {}", e.getMessage());
        e.printStackTrace();
        return "error";
    }
}
