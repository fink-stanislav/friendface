package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.queryhandling.QueryExecutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

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
        } catch (Exception e) {
            entityTransaction.rollback();
            throw new PersistenceException(e);
        }
    }

    public <T> T getById(Object id, Class<T> entityClass) {
        return entityManager.find(entityClass, id);
    }
}
