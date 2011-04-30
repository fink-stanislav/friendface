package net.friendface.friendface.application;

import net.friendface.friendface.service.FriendfaceService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: S. Fink
 * Date: 3/6/11
 * Time: 11:59 PM
 */
public class Initializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FriendfaceService.getService();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        FriendfaceService.stop();
    }
}
