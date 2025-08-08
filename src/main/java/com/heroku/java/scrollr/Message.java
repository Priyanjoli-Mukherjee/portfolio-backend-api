package com.heroku.java.scrollr;

import java.util.Date;
import java.util.UUID;

public class Message {
    private UUID id;
    private String text;
    private String twitterHandle;
    private Date timestamp;

    public UUID getId() {
        return id;
    }

    public Message setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public Message setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Message setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
