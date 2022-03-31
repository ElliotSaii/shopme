package com.shopme.shopme.exception;


public class CustomError extends IllegalArgumentException{

    public CustomError(String msg)
    {
    super(msg);
    }
}
