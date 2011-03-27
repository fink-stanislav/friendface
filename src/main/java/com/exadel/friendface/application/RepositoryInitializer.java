package com.exadel.friendface.application;

import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 11:06 PM
 */

public class RepositoryInitializer {
    private String repositoryHome = "C:\\friendface\\content\\";
    private String repositoryConfig = repositoryHome + "repository.xml";

    Repository repository;

    Session session;

    protected void setUp() throws Exception {
        Repository repository = new TransientRepository(repositoryConfig,
                repositoryHome);
        session = repository.login(new SimpleCredentials("testUserName",
                "testPassword".toCharArray()));
    }

    protected void tearDown() throws Exception {
        session.logout();
        session = null;
        repository = null;
    }

    public void testRepositoryConnection() throws Exception {
        Node root = session.getRootNode();
        String messageText = "Hello, World!";
        // Store content
        Node hello = root.addNode("hello");
        Node world = hello.addNode("world");
        world.setProperty("message", messageText);
        session.save();
        // Retrieve content
        Node node = root.getNode("hello/world");
        // Remove content
        root.getNode("hello").remove();
        session.save();
    }
}
