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
        @NamedQuery(name = "getFriendById", query = "select f from Friend f where f.id = :recordId"),
        @NamedQuery(name = "getUserFriends", query = "select f from Friend f where f.user.id = :userId")
})
public class Friend {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "friendId")
    private User friend;
    private Boolean isApproved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
