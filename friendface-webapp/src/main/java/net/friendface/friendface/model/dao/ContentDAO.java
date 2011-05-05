package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.entities.Identifiable;

/**
 * Author: S. Fink
 * Date: 5/5/11
 * Time: 11:24 PM
 */

public interface ContentDAO {
    String getPath(Identifiable entity);
}
