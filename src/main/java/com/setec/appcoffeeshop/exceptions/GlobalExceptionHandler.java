package com.setec.appcoffeeshop.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Hidden
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(MyResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFound(
            MyResourceNotFoundException ex,
            WebRequest request) {

        ErrorMessage error = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // fallback (optional but recommended)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(
            Exception ex,
            WebRequest request) {

        ErrorMessage error = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
