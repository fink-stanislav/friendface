package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.providers.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:10 PM
 */

public class JpaDAO {
    private EntityManager entityManager;

    public JpaDAO() {
        entityManager = EntityManagerProvider.getInstance().getEntityManager();
    }

    public <T> void removeEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entity);
        entityTransaction.commit();
    }

    public <T> void updateEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(entity);
        entityTransaction.commit();
    }

    public <T> void persistEntities(T... entities) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        for (T entity : entities) {
            entityManager.persist(entity);
        }
        entityTransaction.commit();
    }

    public <T> void persistEntity(T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(entity);
        entityTransaction.commit();
    }

    public <T> T find(Object id, Class<T> entityClass) {
        return entityManager.find(entityClass, id);
    }

    public <T> T executeNamedQuery(String queryName, Class<T> entityClass, String paramName, Object paramValue) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, entityClass);
        query.setParameter(paramName, paramValue);
        return query.getSingleResult();
    }

    public <T> List<T> executeNamedQueryList(String queryName, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, entityClass);
        return query.getResultList();
    }

    public <T> List<T> executeNamedQueryList(String queryName, Class<T> entityClass, String paramName, Object paramValue) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, entityClass);
        query.setParameter(paramName, paramValue);
        return query.getResultList();
    }

    public <T> List<T> executeNamedQueryList(String queryName, Class<T> entityClass, Map<String, Object> params) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, entityClass);
        Set<Map.Entry<String, Object>> paramsEntrySet = params.entrySet();
        for (Map.Entry<String, Object> entry : paramsEntrySet) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
}
