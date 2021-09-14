package com.neosoft.user.exception;

public class UserCreationException extends Exception{

	
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public UserCreationException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserCreationException(String message) {
		this.message = message;
	}

	public UserCreationException(Throwable tx) {
		this.message = tx.getMessage();
	}
}
