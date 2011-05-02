package net.friendface.friendface.model.entities;

import javax.jcr.Binary;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:00
 */

@Entity
@Table(name = "wall_messages")
@NamedQueries(value = {
        @NamedQuery(name = "getMessageByUser",
                query = "select m from WallMessage m where m.receiver = :rec")
})
public class WallMessage implements ContentEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;
    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;
    @Transient
    private Binary content;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public <T> T getContent() {
        return (T) content;
    }

    public <T> void setContent(T content) {
        this.content = (Binary) content;
    }
}
