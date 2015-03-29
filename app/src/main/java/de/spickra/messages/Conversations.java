package de.spickra.messages;

import java.util.List;

public class Conversations {
    private final List<Message> conversations;

    public Conversations(List<Message> conversations) {
        this.conversations = conversations;
    }

    public List<Message> getConversations() {
        return conversations;
    }
}
