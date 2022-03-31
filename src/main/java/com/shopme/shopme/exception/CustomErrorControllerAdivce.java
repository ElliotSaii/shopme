package com.shopme.shopme.exception;

import org.hibernate.dialect.CUBRIDDialect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomErrorControllerAdivce {
    @ExceptionHandler(value = CustomError.class)
    public ResponseEntity<String> handle(CustomError error)
    {
        return new ResponseEntity<String>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationExecption.class)
    public ResponseEntity<String> handleAuthentication(AuthenticationExecption execption)
    {
        return new ResponseEntity<>(execption.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ProductNotExistsException.class)
    public ResponseEntity<String> handleProduct(ProductNotExistsException exception)
    {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
