package com.exadel.friendface.service;

import com.exadel.friendface.model.provider.EntityManagerProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: S. Fink
 * Date: 3/6/11
 * Time: 11:59 PM
 */
public class Initializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerProvider.getInstance();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EntityManagerProvider.getInstance().close();
    }
}
