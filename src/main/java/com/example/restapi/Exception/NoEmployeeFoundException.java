package com.example.restapi.Exception;

public class NoEmployeeFoundException extends RuntimeException{
    public NoEmployeeFoundException() {
        super("No employee is found");
    }
}
