package com.exadel.friendface.model.dao.jcr;

import com.exadel.friendface.model.dao.*;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:11 PM
 */

public class JcrDAOFactory extends DAOFactory {
    @Override
    public RepoDAO getRepoDAO() {
        return new JcrRepoDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        throw new UnsupportedOperationException("Non-JCR operation.");
    }

    @Override
    public FriendsDAO getFriendsDAO() {
        throw new UnsupportedOperationException("Non-JCR operation.");
    }
}
