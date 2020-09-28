package com.capgemini.go.exception;

public class CustomerException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CustomerException() {}
	public CustomerException(String message)
	{
		super(message);
	}

}
