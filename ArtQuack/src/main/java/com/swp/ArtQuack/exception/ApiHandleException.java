package com.swp.ArtQuack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiHandleException {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badRequest(BadRequest badRequest){
        return new ResponseEntity<String>(badRequest.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
