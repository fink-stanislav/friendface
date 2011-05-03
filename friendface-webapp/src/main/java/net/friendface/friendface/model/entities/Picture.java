package net.friendface.friendface.model.entities;

import javax.jcr.Binary;
import javax.persistence.*;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 8:43 PM
 */

@Entity
@Table(name = "pictures")
public class Picture implements ContentEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;
    private String title;
    @Transient
    public Binary content;

    public Integer getId() {
        return null;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Binary getContent() {
        return null;
    }

    public void setContent(Binary content) {

    }
}
