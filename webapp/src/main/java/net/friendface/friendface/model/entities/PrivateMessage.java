package net.friendface.friendface.model.entities;

import javax.jcr.Binary;
import javax.persistence.*;
import java.util.Date;


/**
 * Author: S. Fink
 * Date: 04.06.11
 * Time: 0:13
 */

@Entity
@Table(name = "private_messages")
@NamedQueries(value = {
        @NamedQuery(name = "getPrivateMessages", query = "select m from PrivateMessage m where m.receiver = :rec and m.sender = :sen"),
        @NamedQuery(name = "getPrivateMessagesSenders", query = "select m.sender from PrivateMessage m where m.receiver = :rec")
})
public class PrivateMessage implements ContentEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    @Temporal(value = TemporalType.DATE)
    private Date postDate;

    @Transient
    private Binary content;

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public Binary getContent() {
        return content;
    }

    @Override
    public void setContent(Binary content) {
        this.content = content;
    }
}
