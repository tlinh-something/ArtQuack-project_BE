package com.swp.ArtQuack.exception;

public class CourseNotFoundException extends Exception{
    public CourseNotFoundException() {
        super("Course not found.");
    }
}
