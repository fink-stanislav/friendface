package com.exadel.friendface.application;

public class Main {
    public static void main(String[] args) {
        RepositoryInitializer tr = new RepositoryInitializer();
        try {
            tr.setUp();
            tr.testRepositoryConnection();
            tr.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}