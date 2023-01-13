package com.shine.ecommerce.exceptions;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 963770319994745238L;
	
	public CustomerNotFoundException() {
		super("The customer is not valid, hence it cannot be created.");
	}

}
