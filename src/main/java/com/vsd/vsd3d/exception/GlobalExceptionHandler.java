package com.vsd.vsd3d.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>handleNotFound(Exception ex){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error" + ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>>buildResponse(HttpStatus status, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("error", message);
        body.put("status", status.value());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, status);


    }
}
