package fr.ttmc.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@EnableWebMvc
public class Interceptors extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AuthorizationDeniedException.class})
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        logger.error(ex);
        return handleExceptionInternal(ex, "", headers, HttpStatus.UNAUTHORIZED, request);
    }

}