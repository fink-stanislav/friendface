package net.friendface.friendface.model.entities;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.jcr.Binary;
import javax.persistence.*;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 9:14 PM
 */

@Entity
@Table(name = "videos")
@Indexed
public class Video implements ContentEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String title;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Transient
    private Binary content;

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

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }
}
