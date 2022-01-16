package com.theatre.exceptions;

public class CartIsEmptyException extends Throwable{

	private static final long serialVersionUID = -186301776452158729L;
	private static final String message = "Корзина пуста";

	public String getMessage() {
		return message;
	}
	
}
