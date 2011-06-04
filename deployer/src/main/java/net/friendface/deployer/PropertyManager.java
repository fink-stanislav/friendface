package net.friendface.deployer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Manages application properties.
 * {@code PropertyManager} Loads them once and it become available from the whole application.
 *
 * @author S. Fink
 */
public class PropertyManager {
    private static PropertyManager instance = new PropertyManager("deployer.properties");
    private Properties properties;

    public static PropertyManager getPropertyManager() {
        return instance;
    }

    private PropertyManager(String resourceName) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(resourceName);
            if (in == null) {
                throw new RuntimeException();
            }
            properties = new Properties();
            properties.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key.toLowerCase());
    }
}
