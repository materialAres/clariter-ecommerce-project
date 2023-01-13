package com.shine.ecommerce.exceptions;

public class EmptyCustomerListException extends Exception {

	private static final long serialVersionUID = -5016488990658029896L;
	
	public EmptyCustomerListException() {
		super("The customer's list is empty.");
	}

}
