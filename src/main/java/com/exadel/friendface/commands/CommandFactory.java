/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 23.01.11
 * Time: 20:54
 */

package com.exadel.friendface.commands;

public class CommandFactory {
    private static CommandFactory instance;
    private CommandFactory() {}

    public static CommandFactory getInstance() {
        return instance;
    }

    public static Command create(String name) {
        if (name == null || name.equals("entrance")) {
            return new Entrance();
        }
        return null;
    }
}
