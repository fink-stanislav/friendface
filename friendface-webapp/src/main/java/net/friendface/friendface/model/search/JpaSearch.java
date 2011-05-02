package net.friendface.friendface.model.search;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.*;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 9:25 PM
 */

public class JpaSearch {
    private EntityManager entityManager;
    private String[] fields;
    private String query;

    public JpaSearch(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> List<T> find(Map<String, String> searchParams, Class<T> entityClass) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        EntityTransaction transaction = entityManager.getTransaction();
        javax.persistence.Query persistenceQuery;
        try {
            transaction.begin();
            persistenceQuery = fullTextEntityManager.createFullTextQuery(buildLuceneQuery(searchParams), entityClass);
            transaction.commit();
            return persistenceQuery.getResultList();
        } catch (PersistenceException e) {
            transaction.rollback();
            return Collections.emptyList();
        } catch (ParseException e) {
            transaction.rollback();
            return Collections.emptyList();
        }
    }

    public Query buildLuceneQuery(Map<String, String> searchParams) throws ParseException {
        queryDataFromMap(searchParams);
        MultiFieldQueryParser parser =
                new MultiFieldQueryParser(Version.LUCENE_29, fields, new StandardAnalyzer(Version.LUCENE_29));
        return parser.parse(query);
    }

    private void queryDataFromMap(Map<String, String> parameters) {
        List<String> fields = new ArrayList<String>();
        StringBuilder query = new StringBuilder();

        Set<Map.Entry<String, String>> entrySet = parameters.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (isNotBlank(entry.getValue())) {
                fields.add(entry.getKey());
                query.append(entry.getValue()).append(" ");
            }
        }
        this.query = query.toString();
        this.fields = fields.toArray(new String[fields.size()]);
    }
}
