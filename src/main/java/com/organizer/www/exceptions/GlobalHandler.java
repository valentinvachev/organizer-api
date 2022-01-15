package com.organizer.www.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> notFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        return error(HttpStatus.NOT_FOUND, request.getServletPath(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BindingResult bindingErrors = ex.getBindingResult();
        FieldError firstFieldError = bindingErrors.getFieldErrors().get(0);
        return error(HttpStatus.BAD_REQUEST, request.getServletPath(), firstFieldError.getDefaultMessage());
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<?> webClientResponseException(WebClientResponseException ex, HttpServletRequest request) {
        return error(ex.getStatusCode(), request.getServletPath(), ex.getStatusText());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String errorMessage = String.format("Required request parameter '%s'", ex.getParameterName());
        return error(HttpStatus.BAD_REQUEST, request.getServletPath(), errorMessage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchException(HttpServletRequest request) {
        String errorMessage = "Bad request";
        return error(HttpStatus.BAD_REQUEST, request.getServletPath(), errorMessage);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        String errorMessage = "Method not allowed";
        return error(HttpStatus.METHOD_NOT_ALLOWED, request.getServletPath(), errorMessage);
    }

    private ResponseEntity<?> error(HttpStatus httpStatus, String path, String message) {
        return ResponseEntity.status(httpStatus).body(new ErrorBodyInfo(path, message));
    }
}