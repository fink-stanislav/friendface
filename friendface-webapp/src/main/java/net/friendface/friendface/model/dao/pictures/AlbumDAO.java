package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.User;

import javax.jcr.RepositoryException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 5/6/11
 * Time: 9:28 PM
 */

public interface AlbumDAO {
    List<Album> getUserAlbums(User user);

    Album getById(Integer id);

    Integer getPictureCount(Album album);

    void insertAlbum(Album album) throws RepositoryException;

    void deleteAlbum(Album album) throws RepositoryException;

    void updateAlbum(Album album);
}
