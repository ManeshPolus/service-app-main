package com.polus.service.app.exceptions;

public class UsernameNotFoundException extends RuntimeException {
	public UsernameNotFoundException(String message) {
		super(message);
	}
}

