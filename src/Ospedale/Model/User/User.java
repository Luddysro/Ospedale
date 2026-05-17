/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Model.User;

import Ospedale.Model.Serializable;
import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public abstract class User implements Serializable{
    
    protected final long id;
    protected String username;
    protected String firstname;
    protected String lastname;
    protected String password;

    public User(long id, String username, String firstname, String lastname, String password) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }
        public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
public HashMap<String, Object> serialize(){

    HashMap<String, Object> data =
            new HashMap<>();

  data.put("id", getId());
    data.put("firstname", getFirstname());
    data.put("lastname", getLastname());
    data.put("username", getUsername());
    data.put("password", getPassword());

    return data;
}
}
