package com.neosoft.user.exception;

import javassist.SerialVersionUID;
import lombok.Getter;
@Getter
public class UserNotFoundException  extends Exception{
	
	private static final Long SerialVersionUID=1L;
	
	private String message;
	
	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		this.message = message;
	}

	public UserNotFoundException(Throwable tx) {
		this.message = tx.getMessage();
	}

}
