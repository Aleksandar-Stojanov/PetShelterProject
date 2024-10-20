package com.example.demo.model.exceptions;

public class IncorectPersonException extends RuntimeException{
    public IncorectPersonException() {
    }

    public IncorectPersonException(String message) {
        super(message);
    }
}
