package net.friendface.friendface.model.queryhandling;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
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

    private <T> void setupParams(TypedQuery<T> query, ExecutorParams params) {
        Set<String> names = params.getParamNames();
        for (String name : names) {
            query.setParameter(name, params.getParam(name));
        }
    }

    public <T> T executeCountQuery(ExecutorParams params, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(params.getQueryName(), entityClass);
        setupParams(query, params);
        return query.getSingleResult();
    }

    public <T> T executeNamedQuery(ExecutorParams params, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(params.getQueryName(), entityClass);
        setupParams(query, params);
        return query.getSingleResult();
    }

    public <T> List<T> executeNamedQueryList(ExecutorParams params, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(params.getQueryName(), entityClass);
        setupParams(query, params);
        return query.getResultList();
    }
}
