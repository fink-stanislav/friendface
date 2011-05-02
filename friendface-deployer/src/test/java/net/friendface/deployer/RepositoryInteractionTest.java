package net.friendface.deployer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;

import static org.junit.Assert.assertEquals;

/**
 * Author: S. Fink
 * Date: 4/30/11
 * Time: 9:02 PM
 */
public class RepositoryInteractionTest {
    private RepositoryHandler repositoryHandler;
    private Session session;

    @Before
    public void setUp() throws Exception {
        repositoryHandler = new RepositoryHandler();
        session = repositoryHandler.createSession();
    }

    @After
    public void tearDown() throws Exception {
        session.logout();
    }

    @Test
    public void testInteraction() throws Exception {
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

        assertEquals("Strings should be equal", "Hello, World!", str);

        // Remove content
        root.getNode("hello").remove();
        session.save();
    }
}
