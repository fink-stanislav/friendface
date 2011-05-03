package net.friendface.friendface.model.entities;

import javax.persistence.*;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 9:04 PM
 */

@Entity
@Table(name = "albums")
public class Album implements Identifiable {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
