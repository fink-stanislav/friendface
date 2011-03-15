package com.exadel.friendface.model.entities;

import javax.persistence.*;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 1:25 PM
 */

@Entity
@Table(name = "friends")
@NamedQueries(value = {
        @NamedQuery(name = "getApproved",
                query = "select f from Friend f where (f.receiver = :user or f.sender = :user) and f.isApproved = true"),
        @NamedQuery(name = "getPending",
                query = "select f from Friend f where f.sender = :user and f.isApproved = false"),
        @NamedQuery(name = "getProposal",
                query = "select f from Friend f where f.receiver = :user and f.isApproved = false"),
        @NamedQuery(name = "getSingle",
                query = "select f from Friend f where f.receiver = :rec and f.sender = :sen")
})
public class Friend {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;
    private Boolean isApproved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
