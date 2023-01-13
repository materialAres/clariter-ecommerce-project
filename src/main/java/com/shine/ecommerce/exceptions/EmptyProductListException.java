package com.shine.ecommerce.exceptions;

public class EmptyProductListException extends Exception {

	private static final long serialVersionUID = 5617951553035259703L;
	
	public EmptyProductListException() {
		super("The product list is empty.");
	}

}
