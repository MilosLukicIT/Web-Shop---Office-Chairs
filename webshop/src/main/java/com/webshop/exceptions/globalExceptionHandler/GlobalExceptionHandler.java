package com.webshop.exceptions.globalExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.webshop.exceptions.EntityNotFoundException;
import com.webshop.exceptions.WrongUserCredentialsException;
import com.webshop.model.errorResponse.ErrorResponseMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponseMessage handleUserNotFound(EntityNotFoundException e){
        return new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
	
	@ExceptionHandler(value = WrongUserCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponseMessage handleWrongCredentials(WrongUserCredentialsException e){
        return new ErrorResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
