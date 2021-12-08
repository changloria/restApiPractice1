package com.example.restapi.advice;

import com.example.restapi.exception.NoCompanyFoundException;
import com.example.restapi.exception.NoEmployeeFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalControllerAdvice {

    @ExceptionHandler({NoEmployeeFoundException.class})
    public ErrorResponse handleEmployeeNotFound(Exception exception) {
        return new ErrorResponse(404, "Employee Not Found");
    }

    @ExceptionHandler({NoCompanyFoundException.class})
    public ErrorResponse handleCompanyNotFound(Exception exception){
        return new ErrorResponse(404, "Company Not Found.");
    }
}
