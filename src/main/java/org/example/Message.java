package org.example;

import java.util.UUID;

public class Message {
    private final String id = UUID.randomUUID().toString();
    private String text;
    private Long time;
    private String sender;

    public Message(String text, Long time, String sender) {
        this.text = text;
        this.time = time;
        this.sender = sender;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getTime() {
        return time;
    }

    public String getSender() {
        return sender;
    }
}
