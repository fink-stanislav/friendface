package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.entities.Identifiable;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 16.05.11
 * Time: 20:50
 */

public abstract class Operation<T extends Identifiable> {
    protected T entity;

    public abstract void perform() throws RepositoryException;

    protected Operation(T entity) {
        this.entity = entity;
    }
}
