package com.exadel.friendface.validation;

public class ValidationException extends Exception {
	private String message;
	
	public ValidationException() {
		message = "unknown.exc";
	}
	
	public ValidationException(String message) {
		this.message = message;
	}

    @Override
    public String getMessage() {
        return message;
    }
	
	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(message);
		return sb.toString();
	}
}
