package com.heroku.java.scrollr;

import java.util.Date;
import java.util.UUID;

public class Tweet {
    private UUID id;
    private String message;
    private UUID replyingTo;
    private Date timestamp;
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public Tweet setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Tweet setMessage(String message) {
        this.message = message;
        return this;
    }

    public UUID getReplyingTo() {
        return replyingTo;
    }

    public Tweet setReplyingTo(UUID replyingTo) {
        this.replyingTo = replyingTo;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Tweet setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public UUID getUserId() {
        return userId;
    }

    public Tweet setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }
}
