package com.shine.ecommerce.exceptions;

public class ProductNameNotFoundException extends Exception {

	private static final long serialVersionUID = -4650699210250196965L;
	
	public ProductNameNotFoundException() {
		super("The product name does not exist.");
	}

}
