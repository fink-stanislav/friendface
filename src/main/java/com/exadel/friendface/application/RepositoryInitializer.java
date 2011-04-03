package com.exadel.friendface.application;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.value.BinaryImpl;

import javax.jcr.*;
import java.io.*;

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

    public String convertStreamToString(InputStream is) throws IOException {
        /*
         * To convert the InputStream to String we use the
         * Reader.read(char[] buffer) method. We iterate until the
         * Reader return -1 which means there's no more data to
         * read. We use the StringWriter class to produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
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
        Binary pic = new BinaryImpl(getClass().getResourceAsStream("/struts.xml"));
        node.setProperty("pic", pic);

        Property picrec = node.getProperty("pic");
        Binary picBin = picrec.getBinary();
        InputStream in = picBin.getStream();

        String str = convertStreamToString(in);

        // Remove content
        root.getNode("hello").remove();
        session.save();
    }
}
