package com.gb.hw_lesson12_finalproject.exeption;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
