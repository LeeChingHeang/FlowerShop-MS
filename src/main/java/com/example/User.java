package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private int id;
    private String username;
    private String password;
    
    //  using jackson to convert json data to java object
    public User(@JsonProperty("id") int id, @JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.id = id;
        this.username = username;
        this.password=password;
    }
    // public User(int id, String username, String password) {
    //     this.id = id;
    //     this.username = username;
    //     this.password=password;
    // }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
