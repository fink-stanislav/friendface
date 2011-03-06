package com.exadel.friendface.model.providers;

import javax.jcr.Repository;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Author: S. Fink
 * Date: 3/6/11
 * Time: 1:22 AM
 */

public class RepositoryProvider {
    private static RepositoryProvider instance;
    private Repository repository;

    private RepositoryProvider() throws Exception {
        InitialContext initialContext = new InitialContext();
        Context localContext = (Context) initialContext.lookup("java:comp/env");
        Object repository = localContext.lookup("jcr/repository");
        if (repository instanceof Repository) {
            this.repository = (Repository) repository;
        }
    }

    public static RepositoryProvider getInstance() {
        try {
            if (instance == null) {
                instance = new RepositoryProvider();
            }
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

    public Repository getRepository() {
        return repository;
    }
}

