package net.friendface.friendface.model.queryhandling;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Author: S. Fink
 * Date: 23.05.11
 * Time: 23:20
 */

public class SearchQueryExecutor {
    private FullTextEntityManager fullTextEntityManager;

    public SearchQueryExecutor(FullTextEntityManager fullTextEntityManager) {
        this.fullTextEntityManager = fullTextEntityManager;
    }

    public List executeSearchQuery(Class<?> entityClass, SearchQueryParams params) {
        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(entityClass).get();

        org.apache.lucene.search.Query luceneQuery = null;

        Set<String> names = params.getParamNames();

        if (names.size() > 1) {
            BooleanJunction junction = builder.bool();
            for (String name : names) {
                String key = StringUtils.lowerCase((String) params.getParam(name));
                junction.should(builder.keyword().wildcard().onField(name).matching(params.getParam(key)).createQuery());
            }
            luceneQuery = junction.createQuery();
        } else {
            for (String name : names) {
                String key = StringUtils.lowerCase((String) params.getParam(name));
                luceneQuery = builder.keyword().wildcard().onField(name).matching(key).createQuery();
            }
        }

        if (luceneQuery != null) {
            Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, entityClass);
            return fullTextQuery.getResultList();
        } else {
            return Collections.emptyList();
        }
    }
}
