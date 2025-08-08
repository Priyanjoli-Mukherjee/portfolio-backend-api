package com.heroku.java.scrollr;

import java.util.UUID;

public class Conversation {
    private UUID id;
    private UUID[] messageIds;
    private UUID[] userIds;

    public UUID getId() {
        return id;
    }

    public Conversation setId(UUID id) {
        this.id = id;
        return this;
    }

    public UUID[] getMessageIds() {
        return messageIds;
    }

    public Conversation setMessageIds(UUID[] messageIds) {
        this.messageIds = messageIds;
        return this;
    }

    public UUID[] getUserIds() {
        return userIds;
    }

    public Conversation setUserIds(UUID[] userId) {
        this.userIds = userId;
        return this;
    }
}
