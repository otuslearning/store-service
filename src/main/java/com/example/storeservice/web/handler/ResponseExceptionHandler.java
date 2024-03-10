package com.example.storeservice.web.handler;

import com.example.storeservice.exception.ProductDuplicateCreateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductDuplicateCreateException.class)
    @ResponseStatus(HttpStatus.CREATED)
    public void handleDuplicateCreateException(ProductDuplicateCreateException exception) {
       log.info(exception.getMessage());
    }
}
