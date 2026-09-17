package com.parking.builder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> badRequest(IllegalArgumentException e){return Map.of("timestamp",LocalDateTime.now(),"status",400,"error",e.getMessage());}
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> serverError(Exception e){return Map.of("timestamp",LocalDateTime.now(),"status",500,"error","Internal server error");}
}
