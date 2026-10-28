package com.example.demo.exceptionHandler;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message){
        super(message);
    }
}
