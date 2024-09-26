package com.sanofi.project.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erro400(MethodArgumentNotValidException ex) {

        List<FieldError> message = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(message.stream().map(AppropriateMessage::new).toList());
    }

    private record AppropriateMessage(String field, String message) {
        public AppropriateMessage(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
