package com.shine.ecommerce.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = 3109091042581756266L;

	public OrderNotFoundException() {
		super("This order is empty.");
	}
}
