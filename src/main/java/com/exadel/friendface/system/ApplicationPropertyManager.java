package com.exadel.friendface.system;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

/**
 * User: sfink
 * Date: 2/1/11
 * Time: 6:45 PM
 */

public class ApplicationPropertyManager {
    private static ApplicationPropertyManager instance = new ApplicationPropertyManager();

    private Properties properties;

    private ApplicationPropertyManager() {
        try {
            URL resUrl = getClass().getClassLoader().getResource("application.properties");
            FileInputStream inputStream = new FileInputStream(resUrl.getFile());
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public static ApplicationPropertyManager getInstance() {
        return instance;
    }
}
