package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Picture;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 9:43 PM
 */

public interface PictureDAO {
    List<Picture> getPictures(Album album);

    Picture getById(Integer id);

    void insertPicture(Picture picture);

    void deletePicture(Picture picture);

    void updatePicture(Picture picture);
}
