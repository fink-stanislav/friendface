package com.exadel.friendface.model.providers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * User: S. Fink
 * Date: 1/31/11
 * Time: 4:43 PM
 */

public class EntityManagerProvider {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    private static EntityManagerProvider instance = new EntityManagerProvider();

    private EntityManagerProvider() {
        entityManagerFactory = Persistence.createEntityManagerFactory("friendface");
        entityManager = entityManagerFactory.createEntityManager();
        criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
    }

    public static EntityManagerProvider getInstance() {
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void close() {
        entityManagerFactory.close();
        entityManager.close();
    }
}