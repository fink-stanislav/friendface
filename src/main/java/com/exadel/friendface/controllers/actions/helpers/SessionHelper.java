package com.exadel.friendface.controllers.actions.helpers;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:20
 */

public class SessionHelper {
    private Map session;

    public SessionHelper(Map session) {
        this.session = session;
    }

    public <T> T getFromSession(String key) {
        Object object = session.get(key);
        return (T) object;
    }

    public <T> void putToSession(String key, T value) {
        session.put(key, value);
    }

    public void removeFromSession(String key) {
        session.remove(key);
    }
}
