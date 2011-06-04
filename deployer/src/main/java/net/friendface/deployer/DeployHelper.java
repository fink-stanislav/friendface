package net.friendface.deployer;

import javax.jcr.RepositoryException;

import static net.friendface.deployer.PropertyManager.getPropertyManager;

/**
 * Author: S. Fink
 * Date: 04.06.11
 * Time: 16:31
 */

public class DeployHelper {
    public static void truncateDataBase() {
        DataBasePopulator populator;
        populator = new DataBasePopulator(
                getPropertyManager().getProperty("script.truncate")
        );
        try {
            populator.executeStatement();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void createDataBase() {
        DataBasePopulator populator;
        populator = new DataBasePopulator(
                getPropertyManager().getProperty("script.create")
        );
        try {
            populator.executeStatement();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void createRepository() {
        RepositoryHandler repositoryHandler = new RepositoryHandler();
        try {
            repositoryHandler.createSession();
            repositoryHandler.destroySession();
        } catch (RepositoryException e) {
            e.printStackTrace(System.out);
        }
    }
}
