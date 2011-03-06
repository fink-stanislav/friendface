package com.exadel.friendface.application;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:23 PM
 */

public class PropertyManager {
    private Properties properties;

    public PropertyManager(String resourceName) {
        try {
            URL resUrl = getClass().getClassLoader().getResource(resourceName);
            FileInputStream inputStream = new FileInputStream(resUrl.getFile());
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key.toLowerCase());
    }
}
