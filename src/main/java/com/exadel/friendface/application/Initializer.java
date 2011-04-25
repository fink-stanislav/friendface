package com.exadel.friendface.application;

import com.exadel.friendface.model.dao.RepoDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.service.FriendfaceService;

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
