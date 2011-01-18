package com.exadel.friendface.validation;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 8803291353216715189L;
	private String message;
	
	public ValidationException() {
		message = "Unknown validation exception.";
	}
	
	public ValidationException(String message) {
		this.message = message;
	}
	
	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(message);
		return sb.toString();
	}
}
