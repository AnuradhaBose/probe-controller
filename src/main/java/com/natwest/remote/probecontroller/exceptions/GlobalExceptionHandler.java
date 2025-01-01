package com.natwest.remote.probecontroller.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public void IllegalArgumentExceptionHandler(IllegalArgumentException e){
        System.out.println("Index not in range"+e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void GeneralExceptionHandler(Exception e){
        System.out.println(e.getCause());
    }
}
