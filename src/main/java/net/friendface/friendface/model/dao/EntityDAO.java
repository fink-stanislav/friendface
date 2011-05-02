package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.QueryExecutor;
import net.friendface.friendface.model.entities.ContentEntity;
import net.friendface.friendface.model.providers.RepositoryManager;

import javax.jcr.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:10 PM
 */

public class EntityDAO {
    protected EntityManager entityManager;
    protected RepositoryManager repositoryManager;
    protected QueryExecutor queryExecutor;

    public EntityDAO(EntityManager entityManager, RepositoryManager repositoryManager) {
        this.entityManager = entityManager;
        this.repositoryManager = repositoryManager;
        queryExecutor = new QueryExecutor(entityManager);
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

    public <T extends ContentEntity> T retrieveContent(T entity, String path) throws RepositoryException {
        Node node = repositoryManager.getNode(path);
        Property property = node.getProperty(
                Integer.toString(entity.getId())
        );
        entity.setContent(property.getBinary());
        return entity;
    }

    public <T extends ContentEntity> List<T> retrieveContent(List<T> entityList, String path) throws RepositoryException {
        Node node = repositoryManager.getNode(path);
        for (T entity : entityList) {
            Property property = node.getProperty(
                    Integer.toString(entity.getId())
            );
            entity.setContent(property.getBinary());
        }
        return entityList;
    }

    public <T extends ContentEntity> void storeContent(T entity, String path) throws RepositoryException {
        Node node = repositoryManager.getNode(path);
        node.setProperty(Integer.toString(entity.getId()), (Binary) entity.getContent());
        repositoryManager.getSession().save();
    }

    public <T extends ContentEntity> void removeContent(T entity, String path) throws RepositoryException {
        Node node = repositoryManager.getNode(path);
        Property property = node.getProperty(
                Integer.toString(entity.getId())
        );
        property.remove();
        repositoryManager.getSession().save();
    }
}
