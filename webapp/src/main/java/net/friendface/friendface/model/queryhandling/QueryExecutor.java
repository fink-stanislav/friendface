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

    private <T> void setupParams(TypedQuery<T> query, DefaultQueryParams queryParams) {
        Set<String> names = queryParams.getParamNames();
        for (String name : names) {
            query.setParameter(name, queryParams.getParam(name));
        }
    }

    public <T> T executeCountQuery(DefaultQueryParams queryParams, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryParams.getQueryName(), entityClass);
        setupParams(query, queryParams);
        return query.getSingleResult();
    }

    public <T> T executeNamedQuery(DefaultQueryParams queryParams, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryParams.getQueryName(), entityClass);
        setupParams(query, queryParams);
        return query.getSingleResult();
    }

    public <T> List<T> executeNamedQueryList(DefaultQueryParams queryParams, Class<T> entityClass) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryParams.getQueryName(), entityClass);
        setupParams(query, queryParams);
        return query.getResultList();
    }
}
