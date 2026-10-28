package com.example.demo.exceptionHandler;

public class DuplicateResourceFound  extends  RuntimeException{
    public  DuplicateResourceFound(String message){
        super(message);
    }
}
