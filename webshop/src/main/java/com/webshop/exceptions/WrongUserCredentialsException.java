package com.webshop.exceptions;

public class WrongUserCredentialsException extends RuntimeException{
	
	 public WrongUserCredentialsException (String message) {
	        super(message);
	  }

}
