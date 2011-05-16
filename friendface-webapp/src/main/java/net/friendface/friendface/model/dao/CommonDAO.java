package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.entities.Identifiable;

/**
 * Author: S. Fink
 * Date: 16.05.11
 * Time: 23:45
 */

public interface CommonDAO<T extends Identifiable> {
    void insert(T entity);

    void delete(T entity);

    void update(T entity);

    T getById(Integer id);
}
