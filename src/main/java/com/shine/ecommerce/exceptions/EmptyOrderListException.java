package com.shine.ecommerce.exceptions;

public class EmptyOrderListException extends Exception {
	
	private static final long serialVersionUID = -2539434806654701428L;

	public EmptyOrderListException() {
		super("The order list is empty.");
	}

}
