package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.entities.User;
import org.apache.jackrabbit.value.BinaryImpl;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.*;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 7:40 PM
 */

public class JcrHelper {
    private Session session;

    public JcrHelper(Session session) throws RepositoryException {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public String binaryToString(Binary binary) throws Exception {
        InputStream is = binary.getStream();
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

    public Binary stringToBinary(String string) throws IOException {
        InputStream is = new ByteArrayInputStream(string.getBytes());
        return new BinaryImpl(is);
    }

    public void setupRepository(User user) throws RepositoryException {
        Node userNode = addUserNode(user);
        addNode(userNode, "albums");
        addNode(userNode, "videos");
        addNode(userNode, "posts");
        Node messagesNode = addNode(userNode, "messages");
        addNode(messagesNode, "public");
        addNode(messagesNode, "private");
        session.save();
    }

    public void removeRepository(User user) throws RepositoryException {
        Node userNode = getUserNode(user);
        userNode.remove();
        session.save();
    }

    public Node getRootNode() throws RepositoryException {
        return session.getRootNode();
    }

    public Node getNode(Node parent, String name) throws RepositoryException {
        return parent.getNode(name);
    }

    public Node addNode(Node parent, String name) throws RepositoryException {
        return parent.addNode(name);
    }

    public Node getUserNode(User user) throws RepositoryException {
        return getNode(getRootNode(), user.getLoginEmail());
    }

    public Node addUserNode(User user) throws RepositoryException {
        return addNode(getRootNode(), user.getLoginEmail());
    }

    // in photo dao
    public Node getAlbumNode(User user, String albumTitle) throws RepositoryException {
        return getNode(getUserNode(user), "albums").getNode(albumTitle);
    }

    public Node addAlbumNode(User user, String albumTitle) throws RepositoryException {
        return addNode(getUserNode(user).getNode("albums"), albumTitle);
    }
}
