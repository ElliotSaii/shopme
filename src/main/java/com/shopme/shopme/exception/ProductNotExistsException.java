package com.shopme.shopme.exception;

public class ProductNotExistsException extends IllegalArgumentException{
    public ProductNotExistsException(String msg)
    {
        super(msg);
    }
}
