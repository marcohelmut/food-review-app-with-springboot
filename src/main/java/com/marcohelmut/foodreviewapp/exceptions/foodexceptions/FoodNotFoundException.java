package com.marcohelmut.foodreviewapp.exceptions.foodexceptions;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(String message) {
        super(message);
    }
}
