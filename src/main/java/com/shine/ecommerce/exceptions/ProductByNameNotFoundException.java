package com.shine.ecommerce.exceptions;

public class ProductByNameNotFoundException extends Exception {
	
	private static final long serialVersionUID = 4669612816648676420L;

	public ProductByNameNotFoundException() {
		super("The product cannot be found.");
	}

}
