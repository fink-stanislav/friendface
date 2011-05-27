package net.friendface.friendface.view.beans;

import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.utils.StringUtils;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 4:22 PM
 */

public class WallMessageBean extends WallMessage {
    private String textContent;
    private String userName;
    private Integer userId;

    public WallMessageBean(WallMessage message) throws Exception {
        super();
        setId(message.getId());
        setContent(message.getContent());
        setReceiver(message.getReceiver());
        setSender(message.getSender());
        textContent = StringUtils.binaryToString(getContent());
        userName = getSender().getUsername() + " " + getSender().getUserSurname();
        userId = getSender().getId();
    }

    public String getTextContent() {
        return textContent;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserId() {
        return userId;
    }
}
