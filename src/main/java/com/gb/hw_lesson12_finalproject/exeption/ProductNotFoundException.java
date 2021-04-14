package com.gb.hw_lesson12_finalproject.exeption;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String massage){
        super(massage);
    }
}
