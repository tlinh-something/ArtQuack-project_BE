package com.swp.ArtQuack.exception;

public class InstructorNotFoundException extends Exception{
    public InstructorNotFoundException() {
        super("Instructor not found.");
    }
}
