package com.example.student.controller;

import com.example.student.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.student.constant.SchoolConstants.FAILED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> globalException( Exception ex){
        return ResponseEntity.internalServerError().body(new Response(ex.getMessage(),FAILED));
    }
}
