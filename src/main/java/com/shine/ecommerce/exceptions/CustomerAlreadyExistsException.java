package com.shine.ecommerce.exceptions;

public class CustomerAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 2978403203254088239L;
	
	public CustomerAlreadyExistsException() {
		super("This customer already exists.");
	}

}
