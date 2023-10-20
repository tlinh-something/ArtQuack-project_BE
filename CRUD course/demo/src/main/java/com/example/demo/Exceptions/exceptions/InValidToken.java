package com.example.demo.Exceptions.exceptions;

public class InValidToken extends RuntimeException {
    public InValidToken(String message) {
        super(message);
    }
}
