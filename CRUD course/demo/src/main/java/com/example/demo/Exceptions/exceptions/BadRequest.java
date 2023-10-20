package com.example.demo.Exceptions.exceptions;

public class BadRequest extends RuntimeException{
    public BadRequest(String message){
        super(message);
    }
}
