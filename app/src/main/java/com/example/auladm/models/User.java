package com.example.auladm.models;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String name;
    private String user;
    private String email;

    public User(int id, String name, String user, String email) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nNome: " + name + "; id: " + id;
    }
}
