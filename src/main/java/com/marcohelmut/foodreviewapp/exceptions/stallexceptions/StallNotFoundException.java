package com.marcohelmut.foodreviewapp.exceptions.stallexceptions;

public class StallNotFoundException extends RuntimeException {
    public StallNotFoundException(String name) {
        super("Stall " + name + " not found in the database");
    }
}
