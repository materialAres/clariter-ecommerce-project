package com.shine.ecommerce.exceptions;

public class CustomerDoesNotExistException extends Exception {

	private static final long serialVersionUID = -5578375396113596100L;
	
	public CustomerDoesNotExistException() {
		super("The customer does not exist.");
	}

}
