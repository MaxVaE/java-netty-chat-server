package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class MessageHandler {
    public static String sendMessage (String stringContent) {
        JSONObject messageJson = new JSONObject(stringContent);
        String text = messageJson.get("text").toString();
        Long time = (Long) messageJson.get("time");
        String sender = messageJson.get("sender").toString();


        Message message = new Message(text, time, sender);
        Main.messages.add(message);

        return "Message sent";
    }

    public static String getAllMessage () {
        JSONArray js = new JSONArray(Main.messages);

        return js.toString();
    }
}
