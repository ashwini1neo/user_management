package com.neosoft.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UserApiError {

	private String message;
	private String statusCode;
	private LocalDateTime timestamp;

	public UserApiError() {
		this.timestamp = LocalDateTime.now();
	}

	public UserApiError(HttpStatus status, Throwable tx) {
		this.message = tx.getMessage();
		this.statusCode = status.name();
		this.timestamp = LocalDateTime.now();
	}

	public UserApiError(String message, String statusCode, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.timestamp = LocalDateTime.now();
	}

}
