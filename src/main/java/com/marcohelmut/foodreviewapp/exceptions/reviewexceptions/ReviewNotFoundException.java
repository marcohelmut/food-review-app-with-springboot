package com.marcohelmut.foodreviewapp.exceptions.reviewexceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
