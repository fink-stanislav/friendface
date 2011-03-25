package com.exadel.friendface.service.repo;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.RepoDAO;
import com.exadel.friendface.model.entities.User;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 10:32 PM
 */

public class RepoService {
    private static RepoService service = new RepoService();
    private RepoDAO dao;

    private RepoService() {
        try {
            dao = DAOFactory.getDAOFactory().getRepoDAO();
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

    public Node getUserNode(User user) throws RepositoryException {
        dao.setupRepository(user);
        return dao.getUserNode(user);
    }
}
