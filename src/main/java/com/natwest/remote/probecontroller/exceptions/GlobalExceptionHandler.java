package com.natwest.remote.probecontroller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity IllegalArgumentExceptionHandler(IllegalArgumentException e){
        System.out.println("Index not in range"+e.getMessage());
        return new ResponseEntity<>("Index not in range", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity GeneralExceptionHandler(Exception e){
        System.out.println(e.getCause());
        return new ResponseEntity<>("Internal Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
