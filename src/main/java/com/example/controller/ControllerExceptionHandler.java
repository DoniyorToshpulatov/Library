package com.example.controller;

import com.example.ex.BookCreationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class    ControllerExceptionHandler {
    @ExceptionHandler({BookCreationException.class})
    public ResponseEntity<?> handle(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
