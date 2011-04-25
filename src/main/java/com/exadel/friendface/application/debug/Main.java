package com.exadel.friendface.application.debug;

import com.exadel.friendface.model.dao.RepoDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.service.FriendfaceService;
import org.apache.jackrabbit.value.BinaryImpl;

import javax.jcr.Binary;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        RepositoryInitializer tr = new RepositoryInitializer();
        try {
            tr.setUp();
            tr.testRepositoryConnection();

            RepoDAO dao = FriendfaceService.getService().getRepoService().getDao();
            User user = new User();
            user.setLoginEmail("ravenhollm@gmail.com");
            user.setUsername("Stas");
            user.setUserSurname("Fink");
            user.setPasswordHash("123534abc323");
            user.setId(1);

            dao.setupRepository(user);

            InputStream in = ClassLoader.getSystemResourceAsStream("/diagram.png");
            Binary picture = new BinaryImpl(in);
            dao.addPicture(user, "firstalbum", picture);

            // tr.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}