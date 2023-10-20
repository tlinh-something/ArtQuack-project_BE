package com.example.demo.Exceptions.exceptions;

public class EntityNotFound extends RuntimeException{
    public EntityNotFound(String message) {
        super(message);
    }
}
