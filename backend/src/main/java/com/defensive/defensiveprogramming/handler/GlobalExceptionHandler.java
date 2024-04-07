package com.defensive.defensiveprogramming.handler;

import com.defensive.defensiveprogramming.exception.BankClientNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException entityNotFoundException){
        return ResponseEntity
                .badRequest()
                .body(entityNotFoundException.getMessage());
    }
    @ExceptionHandler(BankClientNotFoundException.class)
    public ResponseEntity<?> handleException(){

        return
                ResponseEntity
                        .notFound()
                        .build();

    }

}
