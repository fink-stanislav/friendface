package com.exadel.friendface.model.search;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 9:25 PM
 */

public class JpaSearch {
    private EntityManager entityManager;

    public JpaSearch(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Query buildLuceneQuery(String initialQuery) throws ParseException {
        String[] fields = new String[]{"username"};
        MultiFieldQueryParser parser =
                new MultiFieldQueryParser(Version.LUCENE_29, fields, new StandardAnalyzer(Version.LUCENE_29));

        return parser.parse(initialQuery);
    }

    public <T> List<T> find(String query, Class<T> entityClass) throws Exception {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        javax.persistence.Query persistenceQuery =
                fullTextEntityManager.createFullTextQuery(buildLuceneQuery(query), entityClass);
        transaction.commit();
        return persistenceQuery.getResultList();
    }
}
