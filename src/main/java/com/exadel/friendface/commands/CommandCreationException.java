package com.exadel.friendface.commands;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/26/11
 * Time: 11:36 AM
 */

public class CommandCreationException extends Exception {
    private String message;

    public CommandCreationException() {
        message = "Command creation error.";
    }

    public CommandCreationException(Throwable cause) {
        message = "Command creation error. " + cause.toString();
    }

    @Override
    public String toString() {
        return message;
    }
}
