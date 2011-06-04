package net.friendface.friendface.view.beans;

import net.friendface.friendface.model.entities.Identifiable;
import net.friendface.friendface.model.entities.PrivateMessage;
import net.friendface.friendface.utils.StringUtils;

/**
 * Author: S. Fink
 * Date: 05.06.11
 * Time: 0:07
 */

public class PrivateMessageBean<T extends Identifiable> extends PrivateMessage implements Comparable<T> {

    public PrivateMessageBean(PrivateMessage message) {
        setContent(message.getContent());
        setPostDate(message.getPostDate());
        setId(message.getId());
        setReceiver(message.getReceiver());
        setSender(message.getSender());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PrivateMessage) {
            PrivateMessage message = (PrivateMessage) obj;
            return message.getId().equals(this.getId());
        }
        return false;
    }

    public String getTextContent() {
        return StringUtils.binaryToString(getContent());
    }

    @Override
    public int compareTo(T message) {
        return message.getId() - this.getId();
    }
}
