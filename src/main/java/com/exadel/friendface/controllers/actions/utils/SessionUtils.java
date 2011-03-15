package com.exadel.friendface.controllers.actions.utils;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:20
 */

public class SessionUtils {
    private Map session;

    public SessionUtils(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public <T> T getFromSession(String key) {
        Object object = session.get(key);
        if (object != null && object instanceof String) {
            return (T) object;
        }
        return null;
    }

    public <T> void putToSession(String key, T value) {
        session.put(key, value);
    }

    public void removeFromSession(String key) {
        session.remove(key);
    }
}
