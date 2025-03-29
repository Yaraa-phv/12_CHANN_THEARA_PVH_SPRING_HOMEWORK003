package com.example.springhw03.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    Map<String, String> errors = new HashMap<>();
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
//        Map<String, String> errors = new HashMap<>();

        e.getParameterValidationResults().forEach(parameterErr -> {
            String paramName = parameterErr.getMethodParameter().getParameterName();
            for (var error : parameterErr.getResolvableErrors()){
                errors.put(paramName, error.getDefaultMessage());
            }
        });

        problemDetail.setProperties(Map.of("timestamp", LocalDateTime.now(), "errors", errors));

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");

//        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()){
            errors.put(error.getField() + ": ", error.getDefaultMessage());
        }
        problemDetail.setProperties(Map.of("errors", errors, "timestamp", LocalDateTime.now()));

        return problemDetail;
    }


//    @ExceptionHandler(HandlerMethodValidationException.class)
//    public ProblemDetail forHandlerMethodValidationException(HandlerMethodValidationException e){
//        e.g
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Error");
//        problemDetail.setTitle("Bad Request");
//        problemDetail.setProperty("timestamp", LocalDateTime.now());
//        Map<String, String> errors = new HashMap<>();
//
//
//        problemDetail.setProperty("errors ", e.getMessage());
//
//        return problemDetail;
//    }

}
