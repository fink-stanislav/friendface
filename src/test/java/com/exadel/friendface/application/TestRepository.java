package com.exadel.friendface.application;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.*;

public class TestRepository extends TestCase {
    private String repositoryHome = "C:\\friendface\\content\\";
    private String repositoryConfig = repositoryHome + "repository.xml";
    private Repository repository;
    private Session session;

    protected void setUp() throws Exception {
        repository = new TransientRepository(repositoryConfig,
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
        Property strPr = node.getProperty("message");
        String str = strPr.getString();

        Assert.assertEquals("Strings should be equal", "Hello, World!", str);

        // Remove content
        root.getNode("hello").remove();
        session.save();
    }
}
