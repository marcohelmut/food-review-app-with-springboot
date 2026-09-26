package com.marcohelmut.foodreviewapp.exceptions.stallexceptions;

public class StallNotFoundException extends RuntimeException {
    public StallNotFoundException(String message) {
        super(message);
    }
}
