package com.shine.ecommerce.exceptions;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = 3612028294322206624L;
	
	public ProductNotFoundException() {
		super("The product does not exist.");
	}

}
