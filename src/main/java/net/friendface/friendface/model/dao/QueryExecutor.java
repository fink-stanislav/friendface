package net.friendface.friendface.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: S. Fink
 * Date: 5/1/11
 * Time: 2:37 PM
 */

public class QueryExecutor {
    private EntityManager entityManager;

    public QueryExecutor(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> T executeNamedQuery(String queryName, Class<T> entityClass, String paramName, Object paramValue) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, entityClass);
        query.setParameter(paramName, paramValue);
        return query.getSingleResult();
    }

    public <T> T executeNamedQuery(String queryName, Class<T> entityClass, Map<String, Object> params) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, entityClass);
        Set<Map.Entry<String, Object>> paramsEntrySet = params.entrySet();
        for (Map.Entry<String, Object> entry : paramsEntrySet) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
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
