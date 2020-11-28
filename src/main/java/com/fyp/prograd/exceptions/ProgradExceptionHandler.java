package com.fyp.prograd.exceptions;

import com.fyp.prograd.model.HttpCustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.fyp.prograd.constant.ErrorConstants.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ProgradExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<HttpCustomResponse> jobNotFoundException() {
        return createHttpResponse(BAD_REQUEST, NO_JOB_FOUND);
    }

    private ResponseEntity<HttpCustomResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        HttpCustomResponse httpCustomResponse = new HttpCustomResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message);

        return new ResponseEntity<>(httpCustomResponse, httpStatus);
    }
}
