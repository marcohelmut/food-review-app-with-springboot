package com.marcohelmut.foodreviewapp.exceptions;

import com.marcohelmut.foodreviewapp.exceptions.stallexceptions.StallNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StallNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleStallNotFound(StallNotFoundException e) {
            Map<String, String> response = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


}
