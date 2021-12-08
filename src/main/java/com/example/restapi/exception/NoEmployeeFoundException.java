package com.example.restapi.exception;

public class NoEmployeeFoundException extends RuntimeException{
    public NoEmployeeFoundException() {
        super("No employee is found");
    }
}
