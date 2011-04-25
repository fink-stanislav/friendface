package com.exadel.friendface.model.entities;

import javax.jcr.Binary;
import javax.persistence.*;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:00
 */

@Entity
@Table(name = "wall_messages")
@NamedQueries(value = {
        @NamedQuery(name = "getMessageByUsers",
                query = "select m from WallMessage m where m.receiver = :rec and m.sender = :sen")
})
public class WallMessage {
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
    private Binary message;

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

    public Binary getMessage() {
        return message;
    }

    public void setMessage(Binary message) {
        this.message = message;
    }
}
