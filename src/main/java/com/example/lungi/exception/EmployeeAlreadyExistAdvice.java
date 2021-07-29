package com.example.lungi.exception;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeeAlreadyExistAdvice {
    @ResponseBody
    @ExceptionHandler(EmployeeAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<Object> employeeAlreadyExistHandler(EmployeeAlreadyExistException ex) {
        Map obj = new HashMap();
        obj.put("error", ex.getMessage());
        return ResponseEntity.ok().body(obj);
    }
}
