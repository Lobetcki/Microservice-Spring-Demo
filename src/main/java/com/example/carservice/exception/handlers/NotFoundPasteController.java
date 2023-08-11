package com.example.carservice.exception.handlers;

import com.example.carservice.exception.IdNotFoundException;
import com.example.carservice.exception.InvalidParametersExeption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class NotFoundPasteController {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> notFound() {
        return ResponseEntity.status(404).body("Not Found");
    }

    @ExceptionHandler(InvalidParametersExeption.class)
    public ResponseEntity<?> invalidParam() {
        return ResponseEntity.status(400).body("Bed request");
    }
}
