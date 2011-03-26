package com.exadel.friendface.service.repo;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.RepoDAO;
import com.exadel.friendface.model.entities.User;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 10:32 PM
 */

public class RepoService {
    private static RepoService service;
    private RepoDAO dao;

    private RepoService() {
        try {
            dao = getDAOFactory(DAOFactory.StorageEngineType.jcr).getRepoDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RepoService getService() {
        if (service == null) {
            service = new RepoService();
        }
        return service;
    }
}
