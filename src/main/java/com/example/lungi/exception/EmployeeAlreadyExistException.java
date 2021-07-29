package com.example.lungi.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
    public EmployeeAlreadyExistException(String name) {
        super("Employee with name "+ name + " already exist.");
    }
}
