package com.theatre.exceptions;

public class NoTicketsAvailableException extends Throwable{

	private static final long serialVersionUID = -186301776452158729L;
	private static final String message = "Билеты закончились";

	public String getMessage() {
		return message;
	}
	
}
