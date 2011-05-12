package net.friendface.friendface.view.beans;

import net.friendface.friendface.model.entities.Picture;

import javax.jcr.RepositoryException;
import java.io.InputStream;

/**
 * Author: S. Fink
 * Date: 5/12/11
 * Time: 11:01 PM
 */

public class PictureBean extends Picture {
    private InputStream inputStream;

    public PictureBean(Picture picture) throws RepositoryException {
        setId(picture.getId());
        setAlbum(picture.getAlbum());
        setContent(picture.getContent());
        setTitle(picture.getTitle());
        inputStream = picture.getContent().getStream();
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
