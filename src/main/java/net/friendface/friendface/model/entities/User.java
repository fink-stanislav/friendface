package net.friendface.friendface.model.entities;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

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
@Indexed
public class User implements Identifiable {
    @Id
    @GeneratedValue
    private Integer id;
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String loginEmail;
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String username;
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String userSurname;
    private String passwordHash;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
