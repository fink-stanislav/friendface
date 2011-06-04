package net.friendface.friendface.model.providers;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * User: S. Fink
 * Date: 1/31/11
 * Time: 4:43 PM
 */

public class EntityManagerProvider {
    private EntityManagerFactory entityManagerFactory;
    private FullTextEntityManager fullTextEntityManager;
    private EntityManager entityManager;
    private static EntityManagerProvider instance;

    public void initialize() throws InterruptedException {
        entityManagerFactory = Persistence.createEntityManagerFactory("friendface");
        entityManager = entityManagerFactory.createEntityManager();
        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }

    public static EntityManagerProvider getInstance() {
        if (instance == null) {
            instance = new EntityManagerProvider();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public FullTextEntityManager getFullTextEntityManager() {
        return fullTextEntityManager;
    }

    public void close() {
        entityManagerFactory.close();
        entityManager.close();
    }
}
