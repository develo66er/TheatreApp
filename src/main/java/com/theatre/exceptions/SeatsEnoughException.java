package com.theatre.exceptions;

public class SeatsEnoughException extends Throwable{

	private static final long serialVersionUID = -186301776452158729L;
	private static final String message = "Билет на это место уже продан";

	public String getMessage() {
		return message;
	}
	
}
