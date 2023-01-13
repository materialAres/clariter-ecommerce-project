package com.shine.ecommerce.exceptions;

public class OrderAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 4577009529453670701L;
	
	public OrderAlreadyExistsException() {
		super("The order already exists.");
	}

}
