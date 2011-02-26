package com.exadel.friendface.model.exceptions;

/**
 * User: S. Fink
 * Date: 2/1/11
 * Time: 2:31 PM
 */

public class ModelException extends Exception {
    private String message;

    public ModelException() {
        this.message = "Model exception. ";
    }

    public ModelException(String message) {
        this.message = message;
    }

    public ModelException(String message, Throwable cause) {
        this.message = message + cause.getMessage();
    }

    public ModelException(Throwable cause) {
        this.message = message + cause.getMessage();
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
