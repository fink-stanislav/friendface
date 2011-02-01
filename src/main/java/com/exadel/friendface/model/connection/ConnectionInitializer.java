package com.exadel.friendface.model.connection;

import com.exadel.friendface.system.ApplicationPropertyManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * User: sfink
 * Date: 1/31/11
 * Time: 4:30 PM
 */

public class ConnectionInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionManager.getInstance();
        ApplicationPropertyManager.getInstance();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionManager.getInstance().closeConnection();
    }
}
