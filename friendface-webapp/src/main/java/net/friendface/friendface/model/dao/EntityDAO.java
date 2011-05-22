package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.QueryExecutor;
import net.friendface.friendface.model.entities.ContentEntity;
import net.friendface.friendface.model.providers.RepositoryManager;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:10 PM
 */

public abstract class EntityDAO {
    protected EntityManager entityManager;
    protected RepositoryManager repositoryManager;
    protected QueryExecutor queryExecutor;

    protected EntityDAO(EntityManager entityManager, RepositoryManager repositoryManager) {
        this.entityManager = entityManager;
        this.repositoryManager = repositoryManager;
        queryExecutor = new QueryExecutor(entityManager);
    }

    public void perform(Operation operation) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            operation.perform();
            entityTransaction.commit();
        } catch (PersistenceException e) {
            entityTransaction.rollback();
            throw new PersistenceException(e);
        } catch (RepositoryException e) {
            entityTransaction.rollback();
            throw new PersistenceException(e);
        }
    }

    public <T> T getById(Object id, Class<T> entityClass) {
        return entityManager.find(entityClass, id);
    }
}
