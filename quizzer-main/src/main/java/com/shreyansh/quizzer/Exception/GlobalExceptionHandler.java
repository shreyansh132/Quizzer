package com.shreyansh.quizzer.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorMessage> notFound(
            DataNotFoundException notFoundException, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, notFoundException.getMessage(),ZonedDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ServerIssue.class)
    public ResponseEntity<ErrorMessage> serverIssue(
            ServerIssue serverIssue, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, serverIssue.getMessage(),ZonedDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(CategoryAlreadyExists.class)
    public ResponseEntity<ErrorMessage> categoryExists(
            CategoryAlreadyExists categoryExists, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, categoryExists.getMessage(),ZonedDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
    }
}