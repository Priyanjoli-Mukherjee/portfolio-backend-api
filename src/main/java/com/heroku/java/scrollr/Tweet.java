package com.heroku.java.scrollr;

import java.util.Date;
import java.util.UUID;

public class Tweet {
    private UUID id;
    private String message;
    private String replyingTo;
    private Date timestamp;
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReplyingTo() {
        return replyingTo;
    }

    public void setReplyingTo(String replyingTo) {
        this.replyingTo = replyingTo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
