package com.exadel.friendface.application;

import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 11:06 PM
 */

public class Main {
    public static void main(String[] args) {
        Repository repository = new TransientRepository();
        Session session = null;
        try {
            session = repository.login();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        try {
            String user = session.getUserID();
            String name = repository.getDescriptor(Repository.REP_NAME_DESC);
            System.out.println(
                    "Logged in as " + user + " to a " + name + " repository.");
        } finally {
            session.logout();
        }
    }
}