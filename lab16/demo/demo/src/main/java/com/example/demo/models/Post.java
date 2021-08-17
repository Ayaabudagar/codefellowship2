package com.example.demo.models;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private DBUser applicationUser;
    private String body;
    private String createdAt;

    public Post(){

    }
    public Post(String body, String createdAt, DBUser applicationUser) {
        this.body = body;
        this.createdAt = createdAt;
        this.applicationUser = applicationUser;
    }

    public Post(String body, DBUser dbUser) {
        this.body = body;


    }


    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public DBUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(DBUser applicationUser) {
        this.applicationUser = applicationUser;
    }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
