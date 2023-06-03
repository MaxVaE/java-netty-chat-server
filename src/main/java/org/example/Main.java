package org.example;

import java.util.ArrayList;

public class Main {
    public static final ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        int port = 8888;
        ChatServer server = new ChatServer(port);
        server.start();
    }
}