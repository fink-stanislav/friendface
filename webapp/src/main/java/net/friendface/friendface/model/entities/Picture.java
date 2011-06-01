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
 * Time: 8:43 PM
 */

@Entity
@Table(name = "pictures")
@NamedQueries(value = {
        @NamedQuery(name = "getPicturesByAlbum", query = "select p from Picture p where p.album = :album"),
        @NamedQuery(name = "deleteAlbumPictures", query = "delete from Picture p where p.album = :album")
})
@Indexed
public class Picture implements ContentEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String title;

    @Transient
    public Binary content;

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
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
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }
}
