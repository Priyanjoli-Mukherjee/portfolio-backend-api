package com.heroku.java.scrollr;

import java.util.UUID;

public class ConversationDTO {
    private UUID id;
    private Message[] messages;
    private User[] users;

    public UUID getId() {
        return id;
    }

    public ConversationDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public Message[] getMessages() {
        return messages;
    }

    public ConversationDTO setMessages(Message[] messages) {
        this.messages = messages;
        return this;
    }

    public User[] getUsers() {
        return users;
    }

    public ConversationDTO setUsers(User[] users) {
        this.users = users;
        return this;
    }
}
