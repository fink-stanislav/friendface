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

    private <T> void setupParams(TypedQuery<T> query, QueryExecutorParams paramsQuery) {
        Set<String> names = paramsQuery.getParamNames();
        for (String name : names) {
            query.setParameter(name, paramsQuery.getParam(name));
        }
    }

    public <T> T executeCountQuery(QueryExecutorParams paramsQuery, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(paramsQuery.getQueryName(), entityClass);
        setupParams(query, paramsQuery);
        return query.getSingleResult();
    }

    public <T> T executeNamedQuery(QueryExecutorParams paramsQuery, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(paramsQuery.getQueryName(), entityClass);
        setupParams(query, paramsQuery);
        return query.getSingleResult();
    }

    public <T> List<T> executeNamedQueryList(QueryExecutorParams paramsQuery, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(paramsQuery.getQueryName(), entityClass);
        setupParams(query, paramsQuery);
        return query.getResultList();
    }
}
