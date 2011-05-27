package net.friendface;

import net.friendface.deployer.DataBasePopulator;
import net.friendface.deployer.RepositoryHandler;

import javax.jcr.RepositoryException;

public class Application {
    public static void main(String[] args) {
        /**
         * Repository initialization
         */
        RepositoryHandler repositoryHandler = new RepositoryHandler();
        try {
            repositoryHandler.createSession();
            repositoryHandler.destroySession();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        /**
         * Create database
         */
        DataBasePopulator populator = new DataBasePopulator("sqlscript");
        try {
            populator.createDb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
