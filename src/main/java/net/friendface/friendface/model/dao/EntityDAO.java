package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.entities.ContentEntity;
import net.friendface.friendface.model.providers.EntityManagerProvider;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:10 PM
 */

public class EntityDAO {
    private EntityManager entityManager;
    private QueryExecutor queryExecutor;

    public EntityDAO(EntityManagerProvider provider) {
        entityManager = provider.getEntityManager();
        queryExecutor = new QueryExecutor(entityManager);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public QueryExecutor getQueryExecutor() {
        return queryExecutor;
    }

    public <T> void removeEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.remove(entity);
            entityTransaction.commit();
        } catch (PersistenceException e) {
            entityTransaction.rollback();
            throw new PersistenceException(e);
        }
    }

    public <T> void updateEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(entity);
            entityTransaction.commit();
        } catch (PersistenceException e) {
            entityTransaction.rollback();
            throw new PersistenceException(e);
        }
    }

    public <T> void persistEntities(T... entities) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            for (T entity : entities) {
                entityManager.persist(entity);
            }
            entityTransaction.commit();
        } catch (PersistenceException e) {
            entityTransaction.rollback();
            throw new PersistenceException(e);
        }
    }

    public <T> void persistEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        } catch (PersistenceException e) {
            entityTransaction.rollback();
            throw new PersistenceException(e);
        }
    }

    public <T> T getById(Object id, Class<T> entityClass) {
        return entityManager.find(entityClass, id);
    }

    public <T extends ContentEntity> T retrieveContent(T entity, Node node) throws RepositoryException {
        Property property = node.getProperty(
                Integer.toString(entity.getId())
        );
        entity.setContent(property.getBinary());
        return entity;
    }

    public <T extends ContentEntity> void storeContent(T entity, Node node) throws RepositoryException {
        node.setProperty(Integer.toString(entity.getId()), (Binary) entity.getContent());
    }

    public <T extends ContentEntity> void removeContent(T entity, Node node) throws RepositoryException {
        Property property = node.getProperty(
                Integer.toString(entity.getId())
        );
        property.remove();
    }
}
