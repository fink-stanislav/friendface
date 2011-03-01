package com.exadel.friendface.model.connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static com.exadel.friendface.model.connection.EntityManagerProvider.getEntityManagerProvider;

/**
 * User: S. Fink
 * Date: 1/31/11
 * Time: 4:30 PM
 */

public class ConnectionInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        getEntityManagerProvider().close();
    }
}
