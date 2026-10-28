package com.example.demo.exceptionHandler;

public class StudentServiceException extends RuntimeException{
    public StudentServiceException(String message){
        super(message);
    }
}
