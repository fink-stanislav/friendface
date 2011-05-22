package net.friendface.friendface.model.entities;

import javax.jcr.Binary;
import javax.persistence.*;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 9:04 PM
 */

@Entity
@Table(name = "albums")
@NamedQueries(value = {
        @NamedQuery(name = "getAlbumsByUser", query = "select a from Album a where a.user = :user"),
        @NamedQuery(name = "getPicturesCount", query = "select count(p) from Picture p where p.album = :album"),
        @NamedQuery(name = "getTitleImageId", query = "select p from Picture p where p.album = :album")
})
public class Album implements ContentEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @Transient
    private Binary content;

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public Binary getContent() {
        return null;
    }

    public void setContent(Binary content) {
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
