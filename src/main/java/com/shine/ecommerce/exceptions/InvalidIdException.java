package com.shine.ecommerce.exceptions;

public class InvalidIdException extends Exception {

	private static final long serialVersionUID = 2066811242969744942L;
	
	public InvalidIdException() {
		super("Not a valid id.");
	}

}
