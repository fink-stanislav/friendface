package net.friendface.friendface.model.dao.wallmessage;

import net.friendface.ParentTest;
import net.friendface.friendface.model.entities.WallMessage;
import org.apache.jackrabbit.value.BinaryImpl;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: S. Fink
 * Date: 5/1/11
 * Time: 2:45 PM
 */

public class TestWallMessageDAOImpl extends ParentTest {
    private Binary message;

    @Before
    public void setUpObject() throws IOException {
        InputStream is = new ByteArrayInputStream("test message".getBytes());
        message = new BinaryImpl(is);
    }

    @Test
    public void testAddMessage() throws RepositoryException {
        WallMessage wallMessage = new WallMessage();
        wallMessage.setSender(sender);
        wallMessage.setReceiver(receiver);
        wallMessage.setContent(message);
    }
}
