package com.exadel.friendface.model.entities;

import javax.persistence.*;

/**
 * User: S. Fink
 * Date: 1/28/11
 * Time: 2:50 PM
 */

@Entity
@Table(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "getUserByLogin", query = "select u from User u where u.loginEmail = :loginEmail")
})
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String loginEmail;
    private String username;
    private String userSurname;
    private String passwordHash;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
