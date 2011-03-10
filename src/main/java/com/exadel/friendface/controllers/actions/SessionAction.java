package com.exadel.friendface.controllers.actions;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 3/9/11
 * Time: 10:49 PM
 */

public abstract class SessionAction extends StandardAction implements SessionAware {
    private Map session;

    public void setSession(Map session) {
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
