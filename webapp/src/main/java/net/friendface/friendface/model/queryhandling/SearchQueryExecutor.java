package net.friendface.friendface.model.queryhandling;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;

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

    FullTextQuery buildSearchQuery(Class<?> entityClass, SearchQueryExecutorParams params) {
        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(entityClass).get();

        org.apache.lucene.search.Query luceneQuery = null;

        return fullTextEntityManager.createFullTextQuery(luceneQuery, entityClass);
    }
}
