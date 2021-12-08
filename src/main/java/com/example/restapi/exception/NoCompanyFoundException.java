package com.example.restapi.exception;

public class NoCompanyFoundException extends RuntimeException{
    public NoCompanyFoundException() {
        super("No company is found");
    }
}
