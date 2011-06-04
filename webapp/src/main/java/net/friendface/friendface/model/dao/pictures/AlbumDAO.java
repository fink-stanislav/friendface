package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.User;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 5/6/11
 * Time: 9:28 PM
 */

public interface AlbumDAO {
    List<Album> getUserAlbums(User user);

    Album getById(Integer id);

    Long getPictureCount(Album album);

    Integer getFrontPictureId(Album album);

    void insertAlbum(Album album);

    void deleteAlbum(Album album);

    void updateAlbum(Album album);
}
