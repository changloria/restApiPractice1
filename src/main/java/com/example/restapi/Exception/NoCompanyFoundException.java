package com.example.restapi.Exception;

public class NoCompanyFoundException extends RuntimeException{
    public NoCompanyFoundException() {
        super("No company is found");
    }
}
