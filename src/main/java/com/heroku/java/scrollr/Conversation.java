package com.heroku.java.scrollr;

import java.util.UUID;

public class Conversation {
    private UUID id;
    private UUID[] messageIds;
    private UUID[] userIds;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID[] getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(UUID[] messageIds) {
        this.messageIds = messageIds;
    }

    public UUID[] getUserIds() {
        return userIds;
    }

    public void setUserIds(UUID[] userId) {
        this.userIds = userId;
    }
}
