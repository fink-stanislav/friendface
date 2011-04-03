package com.exadel.friendface.model.dao.mixed;

import com.exadel.friendface.model.dao.*;

/**
 * Author: S. Fink
 * Date: 3/29/11
 * Time: 11:59 PM
 */

public class MixedFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new MixedUserDAO();
    }

    @Override
    public FriendsDAO getFriendsDAO() {
        throw new UnsupportedOperationException("Not mixed operation.");
    }

    @Override
    public WallMessageDAO getMessageDAO() {
        return new MixedWallMessageDAO();
    }
}
