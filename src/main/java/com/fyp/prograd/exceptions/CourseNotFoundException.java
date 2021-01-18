package com.fyp.prograd.exceptions;

public class CourseNotFoundException extends Exception{
    public CourseNotFoundException() {
        super();
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
