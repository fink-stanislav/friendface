package net.friendface;

import net.friendface.deployer.RepositoryHandler;
import net.friendface.deployer.model.providers.EntityManagerProvider;

import javax.jcr.RepositoryException;

public class Application {
    public static void main(String[] args) {
//        RepositoryHandler repositoryHandler = new RepositoryHandler();
//        try {
//            repositoryHandler.createSession();
//            repositoryHandler.destroySession();
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//        }

        try {
            EntityManagerProvider.getInstance().initialize();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
