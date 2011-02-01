package com.exadel.friendface.util;

/**
 * User: sfink
 * Date: 2/1/11
 * Time: 7:38 PM
 */

public class Pair {
    private String key;
    private Object value;

    public Pair(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
