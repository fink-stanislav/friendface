package net.friendface.friendface.controllers.actions.helpers;

import java.io.Serializable;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:20
 */

public class SessionHelper implements Serializable {
    private Map<String, Object> session;

    public SessionHelper(Map<String, Object> session) {
        this.session = session;
    }

    public Object getFromSession(String key) {
        return session.get(key);
    }

    public void putToSession(String key, Object value) {
        session.put(key, value);
    }

    public void removeFromSession(String key) {
        session.remove(key);
    }
}
