package net.friendface.friendface.service.pictures;

import net.friendface.friendface.model.dao.DAOFactory;
import net.friendface.friendface.model.dao.pictures.AlbumDAO;
import net.friendface.friendface.model.dao.pictures.PictureDAO;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.view.beans.AlbumBean;
import net.friendface.friendface.view.beans.PictureBean;
import org.apache.jackrabbit.value.BinaryImpl;

import javax.jcr.RepositoryException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 5/6/11
 * Time: 9:07 PM
 */

public class PicturesService {
    private static PicturesService service;
    private PictureDAO pictureDAO;
    private AlbumDAO albumDAO;

    public static PicturesService getService() throws RepositoryException {
        if (service == null) {
            service = new PicturesService();
        }
        return service;
    }

    private PicturesService() {
        pictureDAO = DAOFactory.getDAOFactory().getPictureDAO();
        albumDAO = DAOFactory.getDAOFactory().getAlbumDAO();
    }

    public Picture getPictureById(Integer id) throws RepositoryException {
        return pictureDAO.getById(id);
    }

    public void addPicture(Album album, String title, File file) throws RepositoryException, IOException {
        Picture picture = new Picture();
        picture.setAlbum(album);
        picture.setTitle(title);
        picture.setContent(
                new BinaryImpl(new FileInputStream(file))
        );
        pictureDAO.insertPicture(picture);
    }

    public void removePicture(Picture picture) throws RepositoryException {
        Album album = picture.getAlbum();
        pictureDAO.deletePicture(picture);
        if (albumDAO.getPictureCount(album) == 0) {
            albumDAO.deleteAlbum(album);
        }
    }

    public Album getAlbumById(Integer id) {
        return albumDAO.getById(id);
    }

    public List<Picture> getAlbumPictures(Album album) throws RepositoryException {
        return pictureDAO.getPictures(album);
    }

    public Integer getAlbumPictureCount(Album album) {
        return albumDAO.getPictureCount(album);
    }

    public List<AlbumBean> getUserAlbums(User user) {
        List<Album> albums = albumDAO.getUserAlbums(user);
        if (albums != null) {
            List<AlbumBean> albumBeans = new ArrayList<AlbumBean>(albums.size());
            for (Album album : albums) {
                AlbumBean albumBean = new AlbumBean(album);
                albumBean.setPictureCount(getAlbumPictureCount(album));
                albumBeans.add(albumBean);
            }
            return albumBeans;
        } else {
            return null;
        }
    }

    public void addAlbum(User user, String title) throws RepositoryException {
        Album album = new Album();
        album.setTitle(title);
        album.setUser(user);
        albumDAO.insertAlbum(album);
    }

    public void removeAlbum(Album album) throws RepositoryException {
        albumDAO.deleteAlbum(album);
    }
}
