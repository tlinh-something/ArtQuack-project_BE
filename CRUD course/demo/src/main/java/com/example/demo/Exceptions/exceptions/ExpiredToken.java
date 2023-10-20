package com.example.demo.Exceptions.exceptions;

public class ExpiredToken  extends RuntimeException{
    public ExpiredToken(String message) {
        super(message);
    }
}
