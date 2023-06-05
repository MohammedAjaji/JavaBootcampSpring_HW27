package com.example.spring_homework27.ControllerAdvice;

import com.example.spring_homework27.ApiException.ApiException;
import com.example.spring_homework27.ApiResponse.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity ApiException (ApiException exception){
        String message = exception.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException (DataIntegrityViolationException exception){
        String message = exception.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullPointerException (NullPointerException exception){
        String message = exception.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity IllegalStateException (IllegalStateException exception){
        String message = exception.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
}
