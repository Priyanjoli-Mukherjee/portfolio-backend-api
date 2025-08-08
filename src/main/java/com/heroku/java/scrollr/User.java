package com.heroku.java.scrollr;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String twitterHandle;
    private String image;

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getTwitterHandle() {
        return twitterHandle;
    }

    public User setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
        return this;
    }

    public String getImage() {
        return image;
    }

    public User setImage(String image) {
        this.image = image;
        return this;
    }
}
