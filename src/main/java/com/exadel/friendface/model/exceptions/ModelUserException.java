package com.exadel.friendface.model.exceptions;

/**
 * User: S. Fink
 * Date: 2/1/11
 * Time: 2:36 PM
 */

public class ModelUserException extends ModelException {
    private String message;

    public ModelUserException() {
        this.message = "User related error. ";
    }

    public ModelUserException(String message) {
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
