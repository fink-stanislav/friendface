package com.exadel.friendface.model.dao.jcr;

import com.exadel.friendface.model.dao.DAOFactoryImpl;
import com.exadel.friendface.model.dao.RepoDAO;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:11 PM
 */

public class JcrDAOFactory extends DAOFactoryImpl {
    @Override
    public RepoDAO getRepoDAO() {
        return new JcrRepoDAO();
    }
}
