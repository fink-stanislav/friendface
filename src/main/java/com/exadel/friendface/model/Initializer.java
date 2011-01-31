package com.exadel.friendface.model;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * User: sfink
 * Date: 1/31/11
 * Time: 4:30 PM
 */

public class Initializer implements ServletContextListener {
    private static Logger logger = Logger.getLogger("logger");

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("context initialized");
        ConnectionManager.getInstance().initConnection();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("context destroyed");
    }
}
