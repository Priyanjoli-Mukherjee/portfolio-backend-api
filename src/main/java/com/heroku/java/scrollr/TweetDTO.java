package com.heroku.java.scrollr;

import java.util.Date;
import java.util.UUID;

public class TweetDTO {
    private UUID id;
    private String name;
    private String twitterHandle;
    private String image;
    private String message;
    private UUID replyingTo;
    private Date timestamp;
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public TweetDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TweetDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public TweetDTO setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
        return this;
    }

    public String getImage() {
        return image;
    }

    public TweetDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TweetDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public UUID getReplyingTo() {
        return replyingTo;
    }

    public TweetDTO setReplyingTo(UUID replyingTo) {
        this.replyingTo = replyingTo;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public TweetDTO setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public UUID getUserId() {
        return userId;
    }

    public TweetDTO setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }
}
